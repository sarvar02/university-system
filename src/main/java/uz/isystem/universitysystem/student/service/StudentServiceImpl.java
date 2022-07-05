package uz.isystem.universitysystem.student.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.student.StudentDto;
import uz.isystem.universitysystem.student.StudentMapper;
import uz.isystem.universitysystem.student.StudentRepository;

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
        return null;
    }

    @Override
    public void create(StudentDto dto) {

    }

    @Override
    public void update(StudentDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<StudentDto> getAll() {
        return null;
    }
}
