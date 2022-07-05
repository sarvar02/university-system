package uz.isystem.universitysystem.mark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.student.Student;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer markId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "student_id")
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name = "journal_id", insertable = false, updatable = false)
    private Journal journal;

    @Column(name = "journal_id")
    private Integer journalId;
}
