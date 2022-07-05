package uz.isystem.universitysystem.journal_subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.isystem.universitysystem.journal.JournalDto;
import uz.isystem.universitysystem.subject.SubjectDto;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalSubjectDto {

    private Integer journalSubjectId;
    private SubjectDto subjectDto;
    @NotNull(message = "Subject id cannot be null")
    private Integer subjectId;
    private JournalDto journalDto;
    @NotNull(message = "Journal id cannot be null")
    private Integer journalId;
}
