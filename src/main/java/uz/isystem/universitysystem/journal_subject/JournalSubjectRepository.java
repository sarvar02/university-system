package uz.isystem.universitysystem.journal_subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalSubjectRepository extends JpaRepository<JournalSubject, Integer> {

}
