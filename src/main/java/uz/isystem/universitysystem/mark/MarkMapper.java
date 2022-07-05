package uz.isystem.universitysystem.mark;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MarkMapper extends BaseMapper<Mark, MarkDto> {

    @Override
    MarkDto toDto(Mark mark);

    @Override
    List<MarkDto> toDto(List<Mark> e);
}
