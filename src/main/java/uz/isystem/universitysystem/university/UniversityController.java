package uz.isystem.universitysystem.university;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.dto.StudentSubjectsDto;
import uz.isystem.universitysystem.dto.UniversityInfoDto;
import uz.isystem.universitysystem.university.service.UniversityService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    private final UniversityService universityService;


    @GetMapping("/{id}")
    public ResponseEntity getUniversity(@PathVariable("id") Integer id){
        UniversityDto universityDto = universityService.getById(id);
        return ResponseEntity.ok(universityDto);
    }

    @PostMapping
    public ResponseEntity createUniversity(@Valid @RequestBody UniversityDto universityDto){
        universityService.create(universityDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/university").toString());
        return ResponseEntity.ok("NEW UNIVERSITY CREATED !");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUniversity(@PathVariable("id") Integer id,
                                           @Valid @RequestBody UniversityDto universityDto){
        universityService.update(universityDto, id);
        return ResponseEntity.ok("University Successfully updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUniversity(@PathVariable("id") Integer id){
        universityService.delete(id);
        return ResponseEntity.ok("University Successfully Deleted !");
    }

    @GetMapping
    public ResponseEntity deleteUniversity(){
        List<UniversityDto> universityDtoList = universityService.getAll();
        return ResponseEntity.ok(universityDtoList);
    }

    @GetMapping("/{universityId}/info")
    @ApiOperation(value = "Get the number of faculties and students of university",
            notes = "Provide a university's id to look up specific university from database",
            response = UniversityInfoDto.class)
    public ResponseEntity<UniversityInfoDto> getUniversityInfoById(@PathVariable("universityId") Integer universityId){
        UniversityInfoDto universityInfoDto = universityService.getUniversityInfo(universityId);
        return new ResponseEntity<>(universityInfoDto, HttpStatus.OK);
    }
}
