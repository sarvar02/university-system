package uz.isystem.universitysystem.university.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.dto.GroupInfoDto;
import uz.isystem.universitysystem.dto.UniversityInfoDto;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.Faculty;
import uz.isystem.universitysystem.faculty.service.FacultyServiceImpl;
import uz.isystem.universitysystem.university.University;
import uz.isystem.universitysystem.university.UniversityDto;
import uz.isystem.universitysystem.university.UniversityMapper;
import uz.isystem.universitysystem.university.UniversityRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UniversityServiceImpl extends AbstractService<UniversityMapper> implements UniversityService{

    private final UniversityRepository universityRepository;
    private final FacultyServiceImpl facultyService;

    public UniversityServiceImpl(UniversityRepository universityRepository, UniversityMapper universityMapper, @Lazy FacultyServiceImpl facultyService) {
        super(universityMapper);
        this.universityRepository = universityRepository;
        this.facultyService = facultyService;
    }

    @Override
    public UniversityDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(UniversityDto dto) {

        University university = mapper.toEntity(dto);
        university.setCreatedDate(LocalDateTime.now());
        university.setIsActive(true);

        // Save To Database
        saveToDatabase(university);
    }

    @Override
    public void update(UniversityDto dto, Integer id) {

        University university = getEntity(id);

        University newUniversity = mapper.toEntity(dto);
        newUniversity.setUniversityId(id);
        newUniversity.setCreatedDate(university.getCreatedDate());
        newUniversity.setIsActive(university.getIsActive());

        // Save To Database
        saveToDatabase(newUniversity);
    }

    @Override
    public void delete(Integer id) {
        University university = getEntity(id);
        university.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(university);
    }

    @Override
    public List<UniversityDto> getAll() {
        List<University> universities = getAllEntities();
        return mapper.toDto(universities);
    }

    @Override
    public UniversityInfoDto getUniversityInfo(Integer universityId){
        UniversityInfoDto universityInfoDto = new UniversityInfoDto();
        List<Faculty> faculties = facultyService.getFacultiesByUniversityId(universityId);
        var ref = new Object() {
            int numberOfStudents = 0;
        };

        faculties.stream().forEach(faculty -> {
            if(facultyService.checkExistFaculty(faculty.getFacultyId())){
                for (GroupInfoDto groupInfoDto : facultyService.getGroupsOfFaculty(faculty.getFacultyId()).getFacultyGroups()) {
                    ref.numberOfStudents += groupInfoDto.getGroupSize();
                }
            }
        });

        universityInfoDto.setNumberOfFaculties(faculties.size());
        universityInfoDto.setNumberOfStudents(ref.numberOfStudents);

        return universityInfoDto;
    }


    // ======== SECONDARY FUNCTIONS ========

    public University getEntity(Integer id){
        return universityRepository.findByUniversityIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("University Not Found !"));
    }

    public List<University> getAllEntities(){
        List<University> universities = universityRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(universities.isEmpty())
            throw new NotFoundException("University Not Found !");
        return universities;
    }

    public void saveToDatabase(University university){
        universityRepository.save(university);
    }

    public boolean isExistUniversity(Integer id){
        return universityRepository.existsByUniversityIdAndDeletedDateIsNullAndIsActive(id, true);
    }
}
