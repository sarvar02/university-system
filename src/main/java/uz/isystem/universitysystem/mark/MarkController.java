package uz.isystem.universitysystem.mark;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.mark.service.MarkService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mark")
public class MarkController {

    private final MarkService markService;


    @GetMapping("/{id}")
    public ResponseEntity<MarkDto> getMark(@PathVariable("id") Integer id){
        MarkDto markDto = markService.getById(id);
        return new ResponseEntity(markDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMark(@Valid @RequestBody MarkDto markDto){
        markService.create(markDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/mark").toString());
        return new ResponseEntity("NEW MARK CREATED", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMark(@PathVariable("id") Integer id,
                                           @Valid @RequestBody MarkDto markDto){
        markService.update(markDto, id);
        return new ResponseEntity("MARK UPDATED SUCCESSFULLY", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable("id") Integer id){
        markService.delete(id);
        return new ResponseEntity("MARK DELETED SUCCESSFULLY", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MarkDto>> getAllMarks(){
        List<MarkDto> markDtos = markService.getAll();
        return new ResponseEntity<>(markDtos, HttpStatus.OK);
    }
}
