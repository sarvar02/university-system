package uz.isystem.universitysystem.group.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.dto.GroupRatingDto;
import uz.isystem.universitysystem.dto.StudentRatingsDto;
import uz.isystem.universitysystem.exception.AlreadyExistException;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.service.FacultyService;
import uz.isystem.universitysystem.group.Group;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.group.GroupMapper;
import uz.isystem.universitysystem.group.GroupRepository;
import uz.isystem.universitysystem.journal.service.JournalService;
import uz.isystem.universitysystem.mark.Mark;
import uz.isystem.universitysystem.mark.service.MarkService;
import uz.isystem.universitysystem.student.Student;
import uz.isystem.universitysystem.student.StudentMapper;
import uz.isystem.universitysystem.student.service.StudentService;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl extends AbstractService<GroupMapper> implements GroupService{

    private final GroupRepository groupRepository;
    private final JournalService journalService;
    private final FacultyService facultyService;
    private final MarkService markService;
    private final StudentService studentService;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, JournalService journalService, FacultyService facultyService, @Lazy MarkService markService, @Lazy StudentService studentService) {
        super(groupMapper);
        this.groupRepository = groupRepository;
        this.journalService = journalService;
        this.facultyService = facultyService;
        this.markService = markService;
        this.studentService = studentService;
    }

    @Override
    public GroupDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(GroupDto dto) {

        if(groupRepository.existsByJournalId(dto.getJournalId()))
            throw new AlreadyExistException("Journal already taken by another group !");

        if(groupRepository.existsByName(dto.getName()))
            throw new AlreadyExistException("Group with this name already exist !");

        // Checking journal
        journalService.existJournal(dto.getJournalId());

        // Checking faculty
        facultyService.existFaculty(dto.getFacultyId());

        Group group = mapper.toEntity(dto);
        group.setIsActive(true);
        group.setCreatedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(group);
    }

    @Override
    public void update(GroupDto dto, Integer id) {

        // Checking journal
        journalService.existJournal(dto.getJournalId());

        // Checking faculty
        facultyService.existFaculty(dto.getFacultyId());

        Group group = getEntity(id);

        Group newGroup = mapper.toEntity(dto);
        newGroup.setGroupId(id);
        newGroup.setCreatedDate(group.getCreatedDate());
        newGroup.setIsActive(group.getIsActive());

        // Save To Database
        saveToDatabase(newGroup);
    }

    @Override
    public void delete(Integer id) {
        Group group = getEntity(id);
        group.setDeletedDate(LocalDateTime.now());

        saveToDatabase(group);
    }

    @Override
    public List<GroupDto> getAll() {
        List<Group> groups = getAllEntities();
        return mapper.toDto(groups);
    }


    @Override
    public List<GroupDto> getGroupsByFacultyId(Integer facultyId) {
        List<Group> groups = groupRepository.findAllByFacultyIdAndDeletedDateIsNullAndIsActive(facultyId, true);
        checkForNotEmpty(groups);

        return mapper.toDto(groups);
    }

    @Override
    public GroupDto getGroupsByJournalId(Integer journalId) {
        Optional<Group> group = groupRepository
                .findByJournalIdAndDeletedDateIsNullAndIsActive( journalId, true);

        if(group.isEmpty())
            throw new NotFoundException("Group Not Found !");
        return mapper.toDto(group.get());
    }

    public GroupRatingDto getStudentsMarkByGroupId(Integer groupId){
        List<Mark> markList = markService.getAllMarksOrderByMarkValue();
        List<Student> studentList = studentService.getStudentsEntityByGroupId(groupId);
        GroupRatingDto groupRatingDto = new GroupRatingDto();

        var ref = new Object() {
            double markValue = 0;
        };
        
        List<StudentRatingsDto> studentRatingsList = new ArrayList<>();
        markList.stream().forEach(mark -> {
            if(studentList.contains(mark.getStudent())){
                ref.markValue += mark.getMarkValue();
                StudentRatingsDto studentRatingsDto =
                        new StudentRatingsDto(mark.getStudent().getName(), mark.getMarkValue());
                studentRatingsList.add(studentRatingsDto);
            }
        });

        // Evaluate average mark of group
        Double average = ref.markValue/studentRatingsList.size();

        groupRatingDto.setStudentRatingsDtoList(studentRatingsList);
        groupRatingDto.setAverageRating(average);

        return groupRatingDto;
    }


    // ======== SECONDARY FUNCTIONS ========

    @Override
    public Group getEntity(Integer groupId){
        return groupRepository.findByGroupIdAndDeletedDateIsNullAndIsActive(groupId, true)
                .orElseThrow(() -> new NotFoundException("Group Not Found !"));
    }

    public List<Group> getAllEntities(){
        List<Group> groups = groupRepository.findAllByDeletedDateIsNullAndIsActive(true);
        checkForNotEmpty(groups);
        return groups;
    }

    public void saveToDatabase(Group group){
        groupRepository.save(group);
    }

    public void checkForNotEmpty(List<Group> groups){
        if(groups.isEmpty())
            throw new NotFoundException("Groups not found");
    }

    public void existGroup(Integer groupId){
        if(!groupRepository.existsByGroupIdAndDeletedDateIsNullAndIsActive(groupId, true))
            throw new NotFoundException("Group with ID not found !");
    }
}
