package uz.isystem.universitysystem.journal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.universitysystem.group.Group;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {

    Optional<Journal> findByJournalIdAndDeletedDateIsNullAndIsActive(Integer journalId, Boolean isActive);

    Optional<Journal> findByJournalNameAndDeletedDateIsNullAndIsActive(String journalName, Boolean isActive);

    List<Journal> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    Boolean existsByJournalIdAndDeletedDateIsNullAndIsActive(Integer journalId, Boolean isActive);

    Boolean existsByJournalNameAndDeletedDateIsNullAndIsActive(String journalName, Boolean isActive);

}
