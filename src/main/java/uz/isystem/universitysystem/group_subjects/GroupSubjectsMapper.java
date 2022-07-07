package uz.isystem.universitysystem.group_subjects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;
import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface GroupSubjectsMapper extends BaseMapper<GroupSubjects, GroupSubjectsDto> {

    @Override
    @Mapping(target = "subjectDto", source = "subject")
    @Mapping(target = "groupDto", source = "group")
    GroupSubjectsDto toDto(GroupSubjects groupSubjects);

    @Override
    List<GroupSubjectsDto> toDto(List<GroupSubjects> e);

}
