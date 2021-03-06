package uz.isystem.universitysystem.faculty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.university.UniversityDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultyDto {

    private Integer facultyId;
    @NotBlank(message = "Faculty name is mandatory")
    private String facultyName;
    private UniversityDto universityDto;
    @NotNull(message = "University id cannot be null")
    private Integer universityId;
}
