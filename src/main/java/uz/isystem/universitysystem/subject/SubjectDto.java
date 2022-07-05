package uz.isystem.universitysystem.subject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.GroupDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Integer subjectId;
    private String name;
    private GroupDto groupDto;
    private Integer groupId;
}
