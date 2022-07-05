package uz.isystem.universitysystem.university;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {

    private Integer universityId;
    @NotBlank(message = "University name is mandatory")
    private String universityName;
    @NotBlank(message = "University address is mandatory")
    private String address;
    private Integer openedYear;
}
