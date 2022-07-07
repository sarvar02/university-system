package uz.isystem.universitysystem.mark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.student.StudentDto;
import uz.isystem.universitysystem.subject.SubjectDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {

    private Integer markId;
    @NotNull(message = "Mark value cannot be null")
    private Integer markValue;
//    private SubjectDto subjectDto;
//    @NotNull(message = "Mark value cannot be null")
//    private Integer subjectId;
    private StudentDto studentDto;
    @NotNull(message = "Student id cannot be null")
    private Integer studentId;
    private JournalDto journalDto;
    @NotNull(message = "Journal id cannot be null")
    private Integer journalId;
}
