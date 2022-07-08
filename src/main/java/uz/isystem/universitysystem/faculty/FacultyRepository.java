package uz.isystem.universitysystem.faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.universitysystem.faculty.Faculty;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Optional<Faculty> findByFacultyIdAndDeletedDateIsNullAndIsActive(Integer facultyId, Boolean isActive);

    List<Faculty> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    List<Faculty> findAllByUniversityIdAndDeletedDateIsNullAndIsActive(Integer universityId, Boolean isActive);

    Boolean existsByFacultyIdAndDeletedDateIsNullAndIsActive(Integer facultyId, Boolean isActive);
}
