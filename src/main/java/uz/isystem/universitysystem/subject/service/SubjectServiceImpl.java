package uz.isystem.universitysystem.subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.subject.SubjectDto;
import uz.isystem.universitysystem.subject.SubjectMapper;
import uz.isystem.universitysystem.subject.SubjectRepository;

import java.util.List;

@Service
public class SubjectServiceImpl extends AbstractService<SubjectMapper> implements SubjectService{

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        super(subjectMapper);
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectDto getById(Integer id) {
        return null;
    }

    @Override
    public void create(SubjectDto dto) {

    }

    @Override
    public void update(SubjectDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<SubjectDto> getAll() {
        return null;
    }
}
