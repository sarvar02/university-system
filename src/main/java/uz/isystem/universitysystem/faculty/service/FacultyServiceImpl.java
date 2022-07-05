package uz.isystem.universitysystem.faculty.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.Faculty;
import uz.isystem.universitysystem.faculty.FacultyDto;
import uz.isystem.universitysystem.faculty.FacultyMapper;
import uz.isystem.universitysystem.faculty.FacultyRepository;
import uz.isystem.universitysystem.group.Group;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacultyServiceImpl extends AbstractService<FacultyMapper> implements FacultyService{

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyMapper facultyMapper) {
        super(facultyMapper);
        this.facultyRepository = facultyRepository;
    }

    @Override
    public FacultyDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(FacultyDto dto) {

        Faculty faculty = mapper.toEntity(dto);
        faculty.setIsActive(true);
        faculty.setCreatedDate(LocalDateTime.now());

        // Save To Database
        saveToDataBase(faculty);
    }

    @Override
    public void update(FacultyDto dto, Integer id) {
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
    }

    @Override
    public List<FacultyDto> getAll() {
        List<Faculty> faculties = getAllEntities();
        return mapper.toDto(faculties);
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
}
