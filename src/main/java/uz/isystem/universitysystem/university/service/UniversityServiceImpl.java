package uz.isystem.universitysystem.university.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.university.UniversityDto;
import uz.isystem.universitysystem.university.UniversityMapper;
import uz.isystem.universitysystem.university.UniversityRepository;

import java.util.List;

@Service
public class UniversityServiceImpl extends AbstractService<UniversityMapper> implements UniversityService{

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository, UniversityMapper universityMapper) {
        super(universityMapper);
        this.universityRepository = universityRepository;
    }

    @Override
    public UniversityDto getById(Integer id) {
        return null;
    }

    @Override
    public void create(UniversityDto dto) {

    }

    @Override
    public void update(UniversityDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<UniversityDto> getAll() {
        return null;
    }
}
