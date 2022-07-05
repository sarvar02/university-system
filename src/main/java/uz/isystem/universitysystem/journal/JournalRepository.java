package uz.isystem.universitysystem.journal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer> {

    Optional<Journal> findByJournalIdAndDeletedDateIsNullAndIsActive(Integer journalId, Boolean isActive);

    List<Journal> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);
}
