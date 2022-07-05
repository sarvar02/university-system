package uz.isystem.universitysystem.journal_subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.universitysystem.journal_subject.service.JournalSubjectService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/journal-subject")
public class JournalSubjectController {

    private final JournalSubjectService journalSubjectService;

    @GetMapping("/{id}")
    public ResponseEntity<JournalSubjectDto> getJournalSubject(@PathVariable("id") Integer id){
        JournalSubjectDto journalSubjectDto = journalSubjectService.getById(id);
        return new ResponseEntity<>(journalSubjectDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createJournalSubject(@RequestBody JournalSubjectDto journalSubjectDto){
        journalSubjectService.create(journalSubjectDto);
        return new ResponseEntity("NEW JOURNAL SUBJECT CREATED !", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJournalSubject(@PathVariable("id") Integer id,
                                                     @Valid @RequestBody JournalSubjectDto journalSubjectDto){
        journalSubjectService.update(journalSubjectDto, id);
        return new ResponseEntity("JOURNAL UPDATED SUCCESSFULLY !", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournalSubject(@PathVariable("id") Integer id){
        journalSubjectService.delete(id);
        return new ResponseEntity("JOURNAL DELETED SUCCESSFULLY !", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JournalSubjectDto>> getAllJournalSubjects(){
        List<JournalSubjectDto> journalSubjectDtos = journalSubjectService.getAll();
        return new ResponseEntity<>(journalSubjectDtos, HttpStatus.OK);
    }

}
