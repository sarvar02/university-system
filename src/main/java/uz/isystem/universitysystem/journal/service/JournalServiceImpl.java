package uz.isystem.universitysystem.journal.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.AlreadyExistException;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.group.Group;
import uz.isystem.universitysystem.group.service.GroupService;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.journal.JournalMapper;
import uz.isystem.universitysystem.journal.JournalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JournalServiceImpl extends AbstractService<JournalMapper> implements JournalService{

    private final JournalRepository journalRepository;
    private final GroupService groupService;

    public JournalServiceImpl(JournalRepository journalRepository, JournalMapper journalMapper, @Lazy GroupService groupService) {
        super(journalMapper);
        this.journalRepository = journalRepository;
        this.groupService = groupService;
    }

    @Override
    public JournalDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(JournalDto dto) {

        if(journalRepository.existsByJournalNameAndDeletedDateIsNullAndIsActive(dto.getJournalName(), true))
            throw new AlreadyExistException("Journal with this NAME already exist !");

        Journal journal = mapper.toEntity(dto);
        journal.setCreatedDate(LocalDateTime.now());
        journal.setIsActive(true);

        // Save To Database
        saveToDatabase(journal);
    }

    @Override
    public void update(JournalDto dto, Integer id) {
        Optional<Journal> check = journalRepository.findByJournalNameAndDeletedDateIsNullAndIsActive(dto.getJournalName(), true);
        if(check.isPresent() && check.get().getJournalId() != id)
            throw new AlreadyExistException("Journal with this NAME already exist !");

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

    public JournalDto getJournalByGroupId(Integer groupId){
        Group group = groupService.getEntity(groupId);

        // Check journal for this group
        existJournal(group.getJournalId());

        return mapper.toDto(getEntity(group.getJournalId()));
    }


    // ======== SECONDARY FUNCTIONS ========

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

    public void existJournal(Integer journalId){
        if(!journalRepository.existsByJournalIdAndDeletedDateIsNullAndIsActive(journalId, true))
            throw new NotFoundException("Journal with this ID not found !");
    }
}
