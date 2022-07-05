package uz.isystem.universitysystem.group;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.faculty.Faculty;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "journal_id", insertable = false, updatable = false)
    private Journal journal;

    @Column(name = "journal_id")
    private Integer journalId;

}
