package uz.isystem.universitysystem.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.dto.StudentInfoDto;
import uz.isystem.universitysystem.dto.StudentSubjectsDto;
import uz.isystem.universitysystem.student.service.StudentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity getOneStudent(@PathVariable("id") Integer id){
        StudentDto studentDto = studentService.getById(id);
        return ResponseEntity.ok(studentDto);
    }

    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody StudentDto studentDto){
        studentService.create(studentDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/student").toString());
        return ResponseEntity.ok("Student successfully created !");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable("id") Integer id,
                                        @Valid @RequestBody StudentDto studentDto){
        studentService.update(studentDto, id);
        return ResponseEntity.ok("Student Successfully updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Integer id){
        studentService.delete(id);
        return ResponseEntity.ok("Student successfully deleted !");
    }

    @GetMapping
    public ResponseEntity getAllStudents(){
        List<StudentDto> studentDtoList = studentService.getAll();
        return ResponseEntity.ok(studentDtoList);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudentDto>> getStudentsByGroupId(@PathVariable("groupId") Integer groupId){
        List<StudentDto> studentDtoList = studentService.getStudentsByGroupId(groupId);
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    // Studentlarni IDsi orqaili u o'qiydigan fanlar ro'yxatini olish
    @GetMapping("/{id}/subjects")
    public ResponseEntity<StudentSubjectsDto> getSubjectsOfStudent(@PathVariable("id") Integer studentId){
        StudentSubjectsDto studentSubjectsDto = studentService.getSubjectsOfStudent(studentId);
        return new ResponseEntity<>(studentSubjectsDto, HttpStatus.OK);
    }

    // Student haqida ba'tafsil ma'lumot olish:
    // Student ismi, Gruppasini nomi, Fakultetini nomi
    @GetMapping("/{id}/info")
    public ResponseEntity<StudentInfoDto> getStudentInfoById(@PathVariable("id") Integer id){
        StudentInfoDto studentInfoDto = studentService.getStudentInfoById(id);
        return new ResponseEntity<>(studentInfoDto, HttpStatus.OK);
    }


}
