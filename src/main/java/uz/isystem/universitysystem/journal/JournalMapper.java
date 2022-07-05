package uz.isystem.universitysystem.journal;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface JournalMapper extends BaseMapper<Journal, JournalDto> {

    @Override
    JournalDto toDto(Journal journal);

    @Override
    List<JournalDto> toDto(List<Journal> e);
}
