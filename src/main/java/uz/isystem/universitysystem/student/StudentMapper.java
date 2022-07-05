package uz.isystem.universitysystem.student;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDto> {

    @Override
    StudentDto toDto(Student student);

    @Override
    List<StudentDto> toDto(List<Student> e);
}
