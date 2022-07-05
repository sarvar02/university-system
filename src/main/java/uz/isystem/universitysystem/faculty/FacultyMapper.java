package uz.isystem.universitysystem.faculty;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FacultyMapper extends BaseMapper<Faculty, FacultyDto> {

    @Override
    default FacultyDto toDto(Faculty faculty) {
        return null;
    }

    @Override
    default List<FacultyDto> toDto(List<Faculty> e) {
        return null;
    }
}
