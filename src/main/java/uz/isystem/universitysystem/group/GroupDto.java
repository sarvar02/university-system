package uz.isystem.universitysystem.group;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.faculty.FacultyDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto {

    private Integer groupId;
    @NotBlank(message = "Group name is mandatory")
    private String name;
    private Integer year;
    private FacultyDto facultyDto;
    @NotNull(message = "Faculty id is mandatory")
    private Integer facultyId;
    private JournalDto journalDto;
    private Integer journalId;

}
