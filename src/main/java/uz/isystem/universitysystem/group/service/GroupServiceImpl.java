package uz.isystem.universitysystem.group.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.group.GroupMapper;
import uz.isystem.universitysystem.group.GroupRepository;

import java.util.List;

@Service
public class GroupServiceImpl extends AbstractService<GroupMapper> implements GroupService{

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
        super(groupMapper);
        this.groupRepository = groupRepository;
    }

    @Override
    public GroupDto getById(Integer id) {
        return null;
    }

    @Override
    public void create(GroupDto dto) {

    }

    @Override
    public void update(GroupDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<GroupDto> getAll() {
        return null;
    }
}
