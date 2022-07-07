package uz.isystem.universitysystem.subject;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper extends BaseMapper<Subject, SubjectDto> {

    @Override
    SubjectDto toDto(Subject subject);

    @Override
    List<SubjectDto> toDto(List<Subject> e);
}
