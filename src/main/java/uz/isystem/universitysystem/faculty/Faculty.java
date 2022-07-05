package uz.isystem.universitysystem.faculty;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.university.University;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facultyId;

    @Column(name = "faculty_name", nullable = false, unique = true)
    private String facultyName;

    @ManyToOne
    @JoinColumn(name = "university_id", insertable = false, updatable = false)
    private University university;

    @Column(name = "university_id")
    private Integer universityId;
}
