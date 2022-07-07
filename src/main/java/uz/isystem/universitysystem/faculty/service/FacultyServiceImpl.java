package uz.isystem.universitysystem.faculty.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.dto.FacultyGroupsDto;
import uz.isystem.universitysystem.dto.GroupInfoDto;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.Faculty;
import uz.isystem.universitysystem.faculty.FacultyDto;
import uz.isystem.universitysystem.faculty.FacultyMapper;
import uz.isystem.universitysystem.faculty.FacultyRepository;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.group.service.GroupService;
import uz.isystem.universitysystem.student.service.StudentService;
import uz.isystem.universitysystem.university.service.UniversityService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyServiceImpl extends AbstractService<FacultyMapper> implements FacultyService{

    private final FacultyRepository facultyRepository;
    private final UniversityService universityService;
    private final GroupService groupService;
    private final StudentService studentService;

    public FacultyServiceImpl(FacultyRepository facultyRepository,
                              FacultyMapper facultyMapper,
                              UniversityService universityService,
                              @Lazy GroupService groupService,
                              @Lazy StudentService studentService) {
        super(facultyMapper);
        this.facultyRepository = facultyRepository;
        this.universityService = universityService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public FacultyDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(FacultyDto dto) {

        boolean isExist = universityService.isExistUniversity(dto.getUniversityId());

        if(!isExist)
            throw new NotFoundException("University Not Found !");

        Faculty faculty = mapper.toEntity(dto);
        faculty.setIsActive(true);
        faculty.setCreatedDate(LocalDateTime.now());

        // Save To Database
        saveToDataBase(faculty);
    }

    @Override
    public void update(FacultyDto dto, Integer id) {

        boolean isExist = universityService.isExistUniversity(dto.getUniversityId());

        if(!isExist)
            throw new NotFoundException("University Not Found !");

        Faculty faculty = getEntity(id);

        Faculty newFaculty = mapper.toEntity(dto);
        newFaculty.setFacultyId(faculty.getFacultyId());
        newFaculty.setCreatedDate(faculty.getCreatedDate());
        newFaculty.setIsActive(faculty.getIsActive());

        // Save To Database
        saveToDataBase(newFaculty);
    }

    @Override
    public void delete(Integer id) {
        Faculty faculty = getEntity(id);
        faculty.setCreatedDate(LocalDateTime.now());

        // Save To Database
        facultyRepository.save(faculty);
    }

    @Override
    public List<FacultyDto> getAll() {
        List<Faculty> faculties = getAllEntities();
        return mapper.toDto(faculties);
    }

    @Override
    public FacultyGroupsDto getGroupsOfFaculty(Integer facultyId) {
        List<GroupDto> groupDtoList = groupService.getGroupsByFacultyId(facultyId);
        List<GroupInfoDto> groupInfoDtos = new ArrayList<>();
        groupDtoList.stream().forEach(groupDto -> {
            GroupInfoDto groupInfoDto = new GroupInfoDto();
            groupInfoDto.setGroupDto(groupDto);
            groupInfoDto.setGroupSize(studentService.getStudentsByGroupId(groupDto.getGroupId()).size());

            groupInfoDtos.add(groupInfoDto);
        });

        return new FacultyGroupsDto(groupInfoDtos);
    }

    // Secondary functions

    public Faculty getEntity(Integer id){
        return facultyRepository.findByFacultyIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("Faculty Not Found !"));
    }

    public List<Faculty> getAllEntities(){
        List<Faculty> faculties = facultyRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(faculties.isEmpty())
            throw new NotFoundException("Faculty Not Found !");
        return faculties;
    }

    public void saveToDataBase(Faculty faculty){
        facultyRepository.save(faculty);
    }

    public void existFaculty(Integer facultyId){
        if(!facultyRepository.existsByFacultyIdAndDeletedDateIsNullAndIsActive(facultyId, true))
            throw new NotFoundException("Faculty with ID is not found !");
    }

}
