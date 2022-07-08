package uz.isystem.universitysystem.subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.AlreadyExistException;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.subject.Subject;
import uz.isystem.universitysystem.subject.SubjectDto;
import uz.isystem.universitysystem.subject.SubjectMapper;
import uz.isystem.universitysystem.subject.SubjectRepository;

import java.time.LocalDateTime;
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
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(SubjectDto dto) {

        if(existsByName(dto.getName()))
            throw new AlreadyExistException("Subject with name already exists !");

        Subject subject = mapper.toEntity(dto);
        subject.setCreatedDate(LocalDateTime.now());
        subject.setIsActive(true);

        // Save To Database
        saveToDatabase(subject);
    }

    @Override
    public void update(SubjectDto dto, Integer id) {
        Subject subject = getEntity(id);

        Subject newSubject = mapper.toEntity(dto);
        newSubject.setSubjectId(id);
        newSubject.setCreatedDate(subject.getCreatedDate());
        newSubject.setIsActive(subject.getIsActive());

        saveToDatabase(newSubject);
    }

    @Override
    public void delete(Integer id) {
        Subject subject = getEntity(id);
        subject.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(subject);
    }

    @Override
    public List<SubjectDto> getAll() {
        List<Subject> subjects = getAllEntities();
        return mapper.toDto(subjects);
    }

    @Override
    public void existSubject(Integer subjectId) {
        if(!subjectRepository.existsBySubjectIdAndDeletedDateIsNullAndIsActive(subjectId, true))
            throw new NotFoundException("Subject with this ID not found !");
    }

    // ======== SECONDARY FUNCTIONS ========

    public Subject getEntity(Integer id){
        return subjectRepository.findBySubjectIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("Subject Not Found !"));
    }

    public List<Subject> getAllEntities(){
        List<Subject> subjects = subjectRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(subjects.isEmpty()){
            throw new NotFoundException("Subject Not Found !");
        }
        return subjects;
    }

    public void saveToDatabase(Subject subject){
        subjectRepository.save(subject);
    }

    public boolean existsByName(String subjectName){
        return subjectRepository.existsByNameAndDeletedDateIsNullAndIsActive(subjectName, true) ? true : false;
    }

}
