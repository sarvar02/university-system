package uz.isystem.universitysystem.group.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.group.Group;
import uz.isystem.universitysystem.group.GroupDto;

import java.util.List;

@Service
public interface GroupService extends GenericCrudService<Group, GroupDto, Integer> {

    List<GroupDto> getGroupsByFacultyId(Integer facultyId);

    GroupDto getGroupsByJournalId(Integer journalId);

}
