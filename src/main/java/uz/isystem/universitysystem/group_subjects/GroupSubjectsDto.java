package uz.isystem.universitysystem.group_subjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.subject.SubjectDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupSubjectsDto {
    private Integer groupSubjectsId;
    private GroupDto groupDto;
    private Integer groupId;
    private SubjectDto subjectDto;
    private Integer subjectId;
}
