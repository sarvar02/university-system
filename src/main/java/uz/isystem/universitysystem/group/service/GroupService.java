package uz.isystem.universitysystem.group.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.group.Group;
import uz.isystem.universitysystem.group.GroupDto;

@Service
public interface GroupService extends GenericCrudService<Group, GroupDto, Integer> {

}
