package uz.isystem.universitysystem.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDto> {

    @Override
    @Mapping(target = "groupDto", source = "group")
    StudentDto toDto(Student student);

    @Override
    List<StudentDto> toDto(List<Student> e);

    @Override
    Student toEntity(StudentDto studentDto);
}
