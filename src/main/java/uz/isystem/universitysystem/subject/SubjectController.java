package uz.isystem.universitysystem.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.subject.service.SubjectService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity getSubject(@PathVariable("id") Integer id){
        SubjectDto subjectDto = subjectService.getById(id);
        return ResponseEntity.ok(subjectDto);
    }

    @PostMapping
    public ResponseEntity createSubject(@Valid @RequestBody SubjectDto subjectDto){
        subjectService.create(subjectDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/subject").toString());
        return ResponseEntity.ok("SUBJECT SUCCESSFULLY CREATED !");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateSubject(@PathVariable("id") Integer id,
                                        @Valid @RequestBody SubjectDto subjectDto){
        subjectService.update(subjectDto, id);
        return ResponseEntity.ok("SUBJECT SUCCESSFULLY UPDATED !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubject(@PathVariable("id") Integer id){
        subjectService.delete(id);
        return ResponseEntity.ok("SUBJECT SUCCESSFULLY DELETED !");
    }

    @GetMapping
    public ResponseEntity getAllSubject(){
        List<SubjectDto> subjectDtoList = subjectService.getAll();
        return ResponseEntity.ok(subjectDtoList);
    }
}
