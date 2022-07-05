package uz.isystem.universitysystem.university;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer universityId;

    @Column(name = "university_name", nullable = false, unique = true)
    private String universityName;

    @Column(name = "address")
    private String address;

    @Column(name = "opened_year")
    private Integer openedYear;

    @Column(name = "is_active")
    public Boolean isActive = false;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    public LocalDateTime deletedDate;
}
