package uz.isystem.universitysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.student.StudentDto;
import uz.isystem.universitysystem.subject.SubjectDto;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectsDto {
    private StudentDto studentDto;
    SubjectsDto subjectsDto;
}
