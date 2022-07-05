package uz.isystem.universitysystem.university;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {

    private Integer universityId;
    private String universityName;
    private String address;
    private Integer openedYear;
}
