package uz.isystem.universitysystem.journal_subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.subject.SubjectDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalSubjectDto {

    private Integer journalSubjectId;
    private SubjectDto subjectDto;
    private Integer subjectId;
    private JournalDto journalDto;
    private Integer journalId;
}
