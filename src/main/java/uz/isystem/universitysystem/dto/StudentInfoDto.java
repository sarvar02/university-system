package uz.isystem.universitysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDto {
    private String studentName;
    private String groupName;
    private String facultyName;
    private String universityName;
    private String universityAddress;
}