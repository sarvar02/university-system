package uz.isystem.universitysystem.student.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.student.Student;
import uz.isystem.universitysystem.student.StudentDto;
import uz.isystem.universitysystem.student.StudentMapper;
import uz.isystem.universitysystem.student.StudentRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl extends AbstractService<StudentMapper> implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        super(studentMapper);
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(StudentDto dto) {

        Student student = mapper.toEntity(dto);
        student.setCreatedDate(LocalDateTime.now());
        student.setIsActive(true);

        // => Saving to DB
        saveToDatabase(student);
    }

    @Override
    public void update(StudentDto dto, Integer id) {
        Student student = getEntity(id);

        Student newStudent = mapper.toEntity(dto);
        newStudent.setStudentId(id);
        newStudent.setCreatedDate(student.getCreatedDate());
        newStudent.setIsActive(student.getIsActive());
        newStudent.setUpdatedDate(LocalDateTime.now());

        saveToDatabase(newStudent);
    }

    @Override
    public void delete(Integer id) {

        Student student = getEntity(id);
        student.setDeletedDate(LocalDateTime.now());

        saveToDatabase(student);
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> studentList = getAllEntities();
        return mapper.toDto(studentList);
    }

    @Override
    public List<StudentDto> getStudentsByGroupId(Integer groupId) {
        List<Student> studentList = studentRepository.findAllByGroupIdAndDeletedDateIsNullAndIsActive(groupId, true);
        if(studentList.isEmpty())
            throw new NotFoundException("Students not found in this group !");
        return mapper.toDto(studentList);
    }


    // Secondary Functions //

    public Student getEntity(Integer studentId){
        return studentRepository.findByStudentIdAndIsActiveAndDeletedDateIsNull(studentId, true)
                .orElseThrow(() -> new NotFoundException("Student not found !"));
    }

    public List<Student> getAllEntities(){
        List<Student> studentList = studentRepository.findAllByDeletedDateIsNullAAndIsActive(true);
        if(studentList.isEmpty())
            throw new NotFoundException("Student not found !");
        return studentList;
    }

    public void saveToDatabase(Student student){
        studentRepository.save(student);
    }
}
