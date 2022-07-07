package uz.isystem.universitysystem.group.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.AlreadyExistException;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.service.FacultyService;
import uz.isystem.universitysystem.group.Group;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.group.GroupMapper;
import uz.isystem.universitysystem.group.GroupRepository;
import uz.isystem.universitysystem.journal.service.JournalService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl extends AbstractService<GroupMapper> implements GroupService{

    private final GroupRepository groupRepository;
    private final JournalService journalService;
    private final FacultyService facultyService;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, JournalService journalService, FacultyService facultyService) {
        super(groupMapper);
        this.groupRepository = groupRepository;
        this.journalService = journalService;
        this.facultyService = facultyService;
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

    // ======== SECONDARY FUNCTIONS ========

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
