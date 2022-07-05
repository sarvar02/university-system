package uz.isystem.universitysystem.journal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.journal.service.JournalService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/journal")
public class JournalController {

    private final JournalService journalService;


    @GetMapping("/{id}")
    public ResponseEntity<JournalDto> getJournal(@PathVariable("id") Integer id){
        JournalDto journalDto = journalService.getById(id);
        return new ResponseEntity<>(journalDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createJournal(@Valid @RequestBody JournalDto journalDto){
        journalService.create(journalDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/mark").toString());
        return new ResponseEntity("NEW JOURNAL CREATED !",HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJournal(@PathVariable("id") Integer id,
                                              @Valid @RequestBody JournalDto journalDto){
        journalService.update(journalDto, id);
        return new ResponseEntity("JOURNAL UPDATED SUCCESSFULLY", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable("id") Integer id){
        journalService.delete(id);
        return new ResponseEntity("JOURNAL DELETED SUCCESSFULLY", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<JournalDto>> getAllJournals(){
        List<JournalDto> journalDtos = journalService.getAll();
        return new ResponseEntity<>(journalDtos, HttpStatus.OK);
    }
}
