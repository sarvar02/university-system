package uz.isystem.universitysystem.faculty;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.dto.FacultyGroupsDto;
import uz.isystem.universitysystem.dto.StudentSubjectsDto;
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

    // Fakultetga tegishli bo'lgan guruhlar va talabalar sonini olish uchun API
    @GetMapping("/{id}/groups-info")
    @ApiOperation(value = "Get the list of groups and the number of students in each group in the faculty",
            notes = "Provide a faculty id to look up specific infos",
            response = FacultyGroupsDto.class)
    public ResponseEntity<FacultyGroupsDto> getGroupsInfoByFacultyId(@PathVariable("id") Integer id){
        FacultyGroupsDto facultyGroupsDto = facultyService.getGroupsOfFaculty(id);
        return new ResponseEntity<>(facultyGroupsDto, HttpStatus.OK);
    }

}
