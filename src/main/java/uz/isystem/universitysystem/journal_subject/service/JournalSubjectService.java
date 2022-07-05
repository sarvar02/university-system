package uz.isystem.universitysystem.journal_subject.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.journal.service.JournalService;
import uz.isystem.universitysystem.journal_subject.JournalSubject;
import uz.isystem.universitysystem.journal_subject.JournalSubjectDto;

@Service
public interface JournalSubjectService extends GenericCrudService<JournalSubject, JournalSubjectDto, Integer> {

}
