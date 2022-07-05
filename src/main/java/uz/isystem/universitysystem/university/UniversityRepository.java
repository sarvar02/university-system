package uz.isystem.universitysystem.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

    Optional<University> findByUniversityIdAndDeletedDateIsNullAndIsActive(Integer universityId, Boolean isActive);

    List<University> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);
}
