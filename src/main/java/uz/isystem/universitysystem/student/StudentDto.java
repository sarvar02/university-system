package uz.isystem.universitysystem.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.group.GroupDto;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Integer studentId;
    @NotBlank(message = "Student name is mandatory")
    private String name;
    private GroupDto groupDto;
    @NotBlank(message = "Student's group id is mandatory")
    private Integer groupId;
}
