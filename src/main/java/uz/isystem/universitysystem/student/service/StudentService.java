package uz.isystem.universitysystem.student.service;

import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.student.Student;
import uz.isystem.universitysystem.student.StudentDto;

import java.util.List;

public interface StudentService extends GenericCrudService<Student, StudentDto, Integer> {
    List<StudentDto> getStudentsByGroupId(Integer groupId);

}
