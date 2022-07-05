package uz.isystem.universitysystem.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.GroupDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Integer studentId;
    private String name;
    private GroupDto groupDto;
    private Integer groupId;
}
