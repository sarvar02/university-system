package uz.isystem.universitysystem.journal.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.journal.JournalMapper;
import uz.isystem.universitysystem.journal.JournalRepository;
import uz.isystem.universitysystem.mark.Mark;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JournalServiceImpl extends AbstractService<JournalMapper> implements JournalService{

    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository, JournalMapper journalMapper) {
        super(journalMapper);
        this.journalRepository = journalRepository;
    }

    @Override
    public JournalDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(JournalDto dto) {

        Journal journal = mapper.toEntity(dto);
        journal.setCreatedDate(LocalDateTime.now());
        journal.setIsActive(true);

        // Save To Database
        saveToDatabase(journal);
    }

    @Override
    public void update(JournalDto dto, Integer id) {
        Journal journal = getEntity(id);

        Journal newJournal = mapper.toEntity(dto);
        newJournal.setJournalId(id);
        newJournal.setCreatedDate(journal.getCreatedDate());
        newJournal.setIsActive(journal.getIsActive());

        // Save To Database
        saveToDatabase(newJournal);
    }

    @Override
    public void delete(Integer id) {
        Journal journal = getEntity(id);
        journal.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(journal);
    }

    @Override
    public List<JournalDto> getAll() {
        List<Journal> journals = getAllEntities();
        return mapper.toDto(journals);
    }

    // Secondary Functions

    public Journal getEntity(Integer id){
        return journalRepository.findByJournalIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("Journal Not Found !"));
    }

    public List<Journal> getAllEntities(){
        List<Journal> journals = journalRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(journals.isEmpty())
            throw new NotFoundException("Journal Not Found !");
        return journals;
    }

    public void saveToDatabase(Journal journal){
        journalRepository.save(journal);
    }
}
