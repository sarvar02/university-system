package uz.isystem.universitysystem.faculty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.university.UniversityDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

    private Integer facultyId;
    private String facultyName;
    private UniversityDto universityDto;
    private Integer universityId;
}
