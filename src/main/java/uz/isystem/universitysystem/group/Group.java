package uz.isystem.universitysystem.group;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.faculty.Faculty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "faculty_id", insertable = false, updatable = false)
    private Faculty faculty;

    @Column(name = "faculty_id")
    private Integer facultyId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "journalId")
    private Journal journal;

    @Column(name = "journal_id")
    private Integer journalId;

    @Column(name = "is_active")
    public Boolean isActive = false;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    public LocalDateTime deletedDate;

}
