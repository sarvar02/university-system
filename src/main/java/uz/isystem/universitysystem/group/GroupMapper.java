package uz.isystem.universitysystem.group;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GroupMapper extends BaseMapper<Group, GroupDto> {

    @Override
    @Mapping(target = "facultyDto", source = "faculty")
    @Mapping(target = "journalDto", source = "journal")
    GroupDto toDto(Group group);

    @Override
    List<GroupDto> toDto(List<Group> e);

}
