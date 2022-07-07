package uz.isystem.universitysystem.mark.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.journal.service.JournalService;
import uz.isystem.universitysystem.mark.Mark;
import uz.isystem.universitysystem.mark.MarkDto;
import uz.isystem.universitysystem.mark.MarkMapper;
import uz.isystem.universitysystem.mark.MarkRepository;
import uz.isystem.universitysystem.student.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarkServiceImpl extends AbstractService<MarkMapper> implements MarkService{

    private final MarkRepository markRepository;
    private final StudentService studentService;
    private final JournalService journalService;

    public MarkServiceImpl(MarkRepository markRepository, MarkMapper markMapper, StudentService studentService, JournalService journalService) {
        super(markMapper);
        this.markRepository = markRepository;
        this.studentService = studentService;
        this.journalService = journalService;
    }

    @Override
    public MarkDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(MarkDto dto) {

        // Checking student
        studentService.existStudent(dto.getStudentId());
        // Checking journal
        journalService.existJournal(dto.getJournalId());

        Mark mark = mapper.toEntity(dto);
        mark.setCreatedDate(LocalDateTime.now());
        mark.setIsActive(true);

        // Save To Database
        saveToDatabase(mark);
    }

    @Override
    public void update(MarkDto dto, Integer id) {
        Mark mark = getEntity(id);

        Mark newMark = mapper.toEntity(dto);
        newMark.setMarkId(id);
        newMark.setCreatedDate(mark.getCreatedDate());
        newMark.setIsActive(mark.getIsActive());

        // Save To Database
        saveToDatabase(newMark);
    }

    @Override
    public void delete(Integer id) {
        Mark mark = getEntity(id);
        mark.setDeletedDate(LocalDateTime.now());

        // Save To Database
        saveToDatabase(mark);
    }

    @Override
    public List<MarkDto> getAll() {
        List<Mark> marks = getAllEntities();
        return mapper.toDto(marks);
    }

    // Secondary Functions

    public Mark getEntity(Integer id){
        return markRepository.findByMarkIdAndDeletedDateIsNullAndIsActive(id, true)
                .orElseThrow(() -> new NotFoundException("Mark Not Found !"));
    }

    public List<Mark> getAllEntities(){
        List<Mark> marks = markRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(marks.isEmpty())
            throw new NotFoundException("Mark Not Found !");
        return marks;
    }

    public void saveToDatabase(Mark mark){
        markRepository.save(mark);
    }
}
