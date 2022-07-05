package uz.isystem.universitysystem.journal_subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.journal_subject.JournalSubjectDto;
import uz.isystem.universitysystem.journal_subject.JournalSubjectMapper;
import uz.isystem.universitysystem.journal_subject.JournalSubjectRepository;

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
        return null;
    }

    @Override
    public void create(JournalSubjectDto dto) {

    }

    @Override
    public void update(JournalSubjectDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<JournalSubjectDto> getAll() {
        return null;
    }
}
