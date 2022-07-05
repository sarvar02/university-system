package uz.isystem.universitysystem.faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.universitysystem.faculty.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

}
