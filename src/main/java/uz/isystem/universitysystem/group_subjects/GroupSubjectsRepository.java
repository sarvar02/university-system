package uz.isystem.universitysystem.group_subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupSubjectsRepository extends JpaRepository<GroupSubjects, Integer> {

    Optional<GroupSubjects> findByGroupSubjectsIdAndDeletedDateIsNullAndIsActive(Integer id, Boolean isActive);

    List<GroupSubjects> findAllByDeletedDateIsNullAndIsActive(Boolean isActive);

    List<GroupSubjects> findAllByGroupIdAndDeletedDateIsNullAndIsActive(Integer groupId, Boolean isActive);

}
