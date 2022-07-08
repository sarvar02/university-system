package uz.isystem.universitysystem.mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    Optional<Mark> findByMarkIdAndDeletedDateIsNullAndIsActive(Integer markId, Boolean isActive);

    List<Mark> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    @Query(value = "select * from marks WHERE deleted_date IS NULL order by mark_value DESC", nativeQuery = true)
    List<Mark> findAllByMarks();
}
