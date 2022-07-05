package uz.isystem.universitysystem.group;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.faculty.FacultyDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Integer groupId;
    private String name;
    private Integer year;
    private FacultyDto facultyDto;
    private Integer facultyId;
    private JournalDto journalDto;
    private Integer journalId;

}
