package uz.isystem.universitysystem.journal.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.journal.JournalMapper;
import uz.isystem.universitysystem.journal.JournalRepository;

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
        return null;
    }

    @Override
    public void create(JournalDto dto) {

    }

    @Override
    public void update(JournalDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<JournalDto> getAll() {
        return null;
    }
}
