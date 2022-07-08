package uz.isystem.universitysystem.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findBySubjectIdAndDeletedDateIsNullAndIsActive(Integer subjectId, Boolean isActive);

    Boolean existsByNameAndDeletedDateIsNullAndIsActive(String subjectName, Boolean isActive);

    List<Subject> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    Boolean existsBySubjectIdAndDeletedDateIsNullAndIsActive(Integer subjectId, Boolean isActive);
}
