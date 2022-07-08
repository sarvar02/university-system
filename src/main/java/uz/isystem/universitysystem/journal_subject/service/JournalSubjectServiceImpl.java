package uz.isystem.universitysystem.journal_subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.journal.service.JournalService;
import uz.isystem.universitysystem.journal_subject.JournalSubject;
import uz.isystem.universitysystem.journal_subject.JournalSubjectDto;
import uz.isystem.universitysystem.journal_subject.JournalSubjectMapper;
import uz.isystem.universitysystem.journal_subject.JournalSubjectRepository;
import uz.isystem.universitysystem.subject.service.SubjectService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalSubjectServiceImpl extends AbstractService<JournalSubjectMapper> implements JournalSubjectService{

    private final JournalSubjectRepository journalSubjectRepository;
    private final SubjectService subjectService;
    private final JournalService journalService;

    public JournalSubjectServiceImpl(JournalSubjectRepository journalSubjectRepository, JournalSubjectMapper journalSubjectMapper, SubjectService subjectService, JournalService journalService) {
        super(journalSubjectMapper);
        this.journalSubjectRepository = journalSubjectRepository;
        this.subjectService = subjectService;
        this.journalService = journalService;
    }

    @Override
    public JournalSubjectDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(JournalSubjectDto dto) {

        // Check subject
        subjectService.existSubject(dto.getSubjectId());

        // Check journal
        journalService.existJournal(dto.getJournalId());

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


    // ======== SECONDARY FUNCTIONS ========

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
