package uz.isystem.universitysystem.journal_subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JournalSubjectRepository extends JpaRepository<JournalSubject, Integer> {

    Optional<JournalSubject> findByJournalSubjectIdAndDeletedDateIsNullAndIsActive(Integer journalSubjectId, Boolean isActive);

    List<JournalSubject> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

}
