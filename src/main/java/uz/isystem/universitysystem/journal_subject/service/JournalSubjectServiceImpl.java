package uz.isystem.universitysystem.journal_subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.journal_subject.JournalSubject;
import uz.isystem.universitysystem.journal_subject.JournalSubjectDto;
import uz.isystem.universitysystem.journal_subject.JournalSubjectMapper;
import uz.isystem.universitysystem.journal_subject.JournalSubjectRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalSubjectServiceImpl extends AbstractService<JournalSubjectMapper> implements JournalSubjectService{

    private final JournalSubjectRepository journalSubjectRepository;

    public JournalSubjectServiceImpl(JournalSubjectRepository journalSubjectRepository, JournalSubjectMapper journalSubjectMapper) {
        super(journalSubjectMapper);
        this.journalSubjectRepository = journalSubjectRepository;
    }

    @Override
    public JournalSubjectDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(JournalSubjectDto dto) {
        JournalSubject journalSubject = mapper.toEntity(dto);
        journalSubject.setCreatedDate(LocalDateTime.now());
        journalSubject.setIsActive(true);

        // Save To Database
        saveToDatabase(journalSubject);
    }

    @Override
    public void update(JournalSubjectDto dto, Integer id) {
        JournalSubject journalSubject = getEntity(id);

        JournalSubject newJournalSubject = mapper.toEntity(dto);
        newJournalSubject.setJournalSubjectId(id);
        newJournalSubject.setCreatedDate(journalSubject.getCreatedDate());
        newJournalSubject.setIsActive(journalSubject.getIsActive());

        // Save To Database
        saveToDatabase(newJournalSubject);
    }

    @Override
    public void delete(Integer id) {
        JournalSubject journalSubject = getEntity(id);
        journalSubject.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(journalSubject);
    }

    @Override
    public List<JournalSubjectDto> getAll() {
        List<JournalSubject> journalSubjects = getAllEntities();
        return mapper.toDto(journalSubjects);
    }

    // Secondary Functions

    public JournalSubject getEntity(Integer id){
        return journalSubjectRepository.findByJournalSubjectIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("JOURNAL SUBJECT NOT FOUND !"));
    }

    public List<JournalSubject> getAllEntities(){
        List<JournalSubject> journalSubjects = journalSubjectRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(journalSubjects.isEmpty())
            throw new NotFoundException("JOURNAL SUBJECT NOT FOUND !");
        return journalSubjects;
    }

    public void saveToDatabase(JournalSubject journalSubject){
        journalSubjectRepository.save(journalSubject);
    }
}
