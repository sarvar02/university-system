package uz.isystem.universitysystem.group_subjects.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.dto.SubjectsDto;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.group.service.GroupService;
import uz.isystem.universitysystem.group_subjects.GroupSubjects;
import uz.isystem.universitysystem.group_subjects.GroupSubjectsDto;
import uz.isystem.universitysystem.group_subjects.GroupSubjectsMapper;
import uz.isystem.universitysystem.group_subjects.GroupSubjectsRepository;
import uz.isystem.universitysystem.subject.SubjectDto;
import uz.isystem.universitysystem.subject.service.SubjectService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupSubjectsServiceImpl extends AbstractService<GroupSubjectsMapper> implements GroupSubjectsService{

    private final GroupSubjectsRepository groupSubjectsRepository;
    private final SubjectService subjectService;
    private final GroupService groupService;

    protected GroupSubjectsServiceImpl(GroupSubjectsMapper mapper, GroupSubjectsRepository groupSubjectsRepository, SubjectService subjectService, GroupService groupService) {
        super(mapper);
        this.groupSubjectsRepository = groupSubjectsRepository;
        this.subjectService = subjectService;
        this.groupService = groupService;
    }

    @Override
    public GroupSubjectsDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(GroupSubjectsDto dto) {

        // Check Group
        groupService.existGroup(dto.getGroupId());
        // Check Subject
        subjectService.existSubject(dto.getSubjectId());

        GroupSubjects groupSubjects = mapper.toEntity(dto);
        groupSubjects.setCreatedDate(LocalDateTime.now());
        groupSubjects.setIsActive(true);

        // Save To Database
        saveToDatabase(groupSubjects);
    }

    @Override
    public void update(GroupSubjectsDto dto, Integer id) {

        // Check Group
        groupService.existGroup(dto.getGroupId());
        // Check Subject
        subjectService.existSubject(dto.getSubjectId());

        GroupSubjects groupSubjects = getEntity(id);

        GroupSubjects newGroupSubject = mapper.toEntity(dto);
        newGroupSubject.setGroupSubjectsId(id);
        newGroupSubject.setCreatedDate(groupSubjects.getCreatedDate());
        newGroupSubject.setIsActive(groupSubjects.getIsActive());

        // Save To Database
        saveToDatabase(newGroupSubject);
    }

    @Override
    public void delete(Integer id) {
        GroupSubjects groupSubjects = getEntity(id);
        groupSubjects.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(groupSubjects);
    }

    @Override
    public List<GroupSubjectsDto> getAll() {
        List<GroupSubjects> groupSubjectsList = getAllEntities();
        return mapper.toDto(groupSubjectsList);
    }

    @Override
    public SubjectsDto getSubjectsByGroupId(Integer groupId){
        List<GroupSubjects> groupSubjectsList = groupSubjectsRepository.findAllByGroupIdAndDeletedDateIsNullAndIsActive(groupId, true);
        List<SubjectDto> subjectsDtoList = new ArrayList<>();
        mapper.toDto(groupSubjectsList).stream()
                .forEach(groupSubjectsDto -> subjectsDtoList.add(groupSubjectsDto.getSubjectDto()));

        SubjectsDto subjectsDto = new SubjectsDto();
        subjectsDto.setSubjectDtoList(subjectsDtoList);
        return subjectsDto;
    }


    // Secondary Functions
    public GroupSubjects getEntity(Integer id){
        return groupSubjectsRepository.findByGroupSubjectsIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("Group Subject Not Found !"));
    }

    public List<GroupSubjects> getAllEntities(){
        List<GroupSubjects> groupSubjectsList = groupSubjectsRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(groupSubjectsList.isEmpty()){
            throw new NotFoundException("Group Subjects Not Found !");
        }
        return groupSubjectsList;
    }

    public void saveToDatabase(GroupSubjects groupSubjects){
        groupSubjectsRepository.save(groupSubjects);
    }
}
