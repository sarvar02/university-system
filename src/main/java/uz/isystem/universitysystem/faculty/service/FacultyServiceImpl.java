package uz.isystem.universitysystem.faculty.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.faculty.FacultyDto;
import uz.isystem.universitysystem.faculty.FacultyMapper;
import uz.isystem.universitysystem.faculty.FacultyRepository;

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
        return null;
    }

    @Override
    public void create(FacultyDto dto) {

    }

    @Override
    public void update(FacultyDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<FacultyDto> getAll() {
        return null;
    }
}
