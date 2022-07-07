package uz.isystem.universitysystem.mark;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.student.Student;
import uz.isystem.universitysystem.subject.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "mark_value")
    private Integer markValue;

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

//    @ManyToOne
//    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
//    private Subject subject;
//
//    @Column(name = "subject_id")
//    private Integer subjectId;

    @Column(name = "is_active")
    public Boolean isActive = false;

    @Column(name = "created_date")
    public LocalDateTime createdDate;

    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    public LocalDateTime deletedDate;
}
