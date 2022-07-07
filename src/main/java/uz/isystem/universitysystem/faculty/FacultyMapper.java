package uz.isystem.universitysystem.faculty;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FacultyMapper extends BaseMapper<Faculty, FacultyDto> {


    @Override
    @Mapping(target = "universityDto", source = "university")
    FacultyDto toDto(Faculty faculty);

    @Override
    List<FacultyDto> toDto(List<Faculty> e);
}
