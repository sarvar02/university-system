package uz.isystem.universitysystem.faculty;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.faculty.service.FacultyService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/{id}")
    public ResponseEntity getFaculty(@PathVariable("id") Integer id){
        FacultyDto facultyDto = facultyService.getById(id);
        return ResponseEntity.ok(facultyDto);
    }

    @PostMapping
    public ResponseEntity createFaculty(@Valid @RequestBody FacultyDto facultyDto){
        facultyService.create(facultyDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/faculty").toString());
        return ResponseEntity.ok("NEW FACULTY CREATED !");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFaculty(@PathVariable("id") Integer id,
                                        @Valid @RequestBody FacultyDto facultyDto){
        facultyService.update(facultyDto, id);
        return ResponseEntity.ok("Faculty Successfully updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity updateFaculty(@PathVariable("id") Integer id){
        facultyService.delete(id);
        return ResponseEntity.ok("Faculty successfully deleted !");
    }

    @GetMapping
    public ResponseEntity getAllFaculties(){
        List<FacultyDto> facultyDtoList = facultyService.getAll();
        return ResponseEntity.ok(facultyDtoList);
    }

}
