package uz.isystem.universitysystem.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByStudentIdAndIsActiveAndDeletedDateIsNull(Integer studentId, Boolean isActive);

    Optional<Student> findByNameAndDeletedDateIsNullAndIsActive(String name, Boolean isActive);

    List<Student> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    List<Student> findAllByGroupIdAndDeletedDateIsNullAndIsActive(Integer groupId, Boolean isActive);

    Boolean existsByStudentIdAndDeletedDateIsNullAndIsActive(Integer studentId, Boolean isActive);

}
