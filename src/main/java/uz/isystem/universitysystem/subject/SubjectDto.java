package uz.isystem.universitysystem.subject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.GroupDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Integer subjectId;
    @NotBlank(message = "Subject name is mandatory")
    private String name;
    private GroupDto groupDto;
    @NotNull(message = "Group id cannot be null")
    private Integer groupId;
}
