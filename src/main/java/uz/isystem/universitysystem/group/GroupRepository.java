package uz.isystem.universitysystem.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByGroupIdAndCreatedDateIsNullAndIsActive(Integer groupId, Boolean isActive);

    List<Group> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    List<Group> findAllByFacultyIdAndDeletedDateIsNullAndIsActive(Integer facultyId, Boolean isActive);

    Optional<Group> findByJournalIdAndDeletedDateIsNullAndIsActive(Integer journalId, Boolean isActive);

    Boolean existsByJournalId(Integer journalId);

    Boolean existsByName(String name);

}
