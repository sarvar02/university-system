package uz.isystem.universitysystem.group;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GroupMapper extends BaseMapper<Group, GroupDto> {

    @Override
    GroupDto toDto(Group group);

    @Override
    List<GroupDto> toDto(List<Group> e);

}
