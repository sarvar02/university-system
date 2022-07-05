package uz.isystem.universitysystem.mark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.student.StudentDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {

    private Integer markId;
    private StudentDto studentDto;
    private Integer studentId;
    private JournalDto journalDto;
    private Integer journalId;
}
