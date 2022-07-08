package uz.isystem.universitysystem.journal.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.journal.JournalDto;

@Service
public interface JournalService extends GenericCrudService<Journal, JournalDto, Integer> {

    public void existJournal(Integer journalId);

    JournalDto getJournalByGroupId(Integer groupId);
}
