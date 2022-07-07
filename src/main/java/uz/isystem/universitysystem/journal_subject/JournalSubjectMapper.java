package uz.isystem.universitysystem.journal_subject;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.isystem.universitysystem._mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface JournalSubjectMapper extends BaseMapper<JournalSubject, JournalSubjectDto> {

    @Override
    @Mapping(target = "subjectDto", source = "subject")
    @Mapping(target = "journalDto", source = "journal")
    JournalSubjectDto toDto(JournalSubject journalSubject);

    @Override
    List<JournalSubjectDto> toDto(List<JournalSubject> e);
}
