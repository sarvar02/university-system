package uz.isystem.universitysystem.university;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UniversityMapper extends BaseMapper<University, UniversityDto> {

    @Override
    UniversityDto toDto(University university);

    @Override
    List<UniversityDto> toDto(List<University> e);
}