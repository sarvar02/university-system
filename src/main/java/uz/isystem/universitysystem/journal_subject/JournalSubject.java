package uz.isystem.universitysystem.journal_subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.Journal;
import uz.isystem.universitysystem.subject.Subject;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "journal_subjects")
public class JournalSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer journalSubjectId;

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @Column(name = "subject_id")
    private Integer subjectId;

    @ManyToOne
    @JoinColumn(name = "journal_id", insertable = false, updatable = false)
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
