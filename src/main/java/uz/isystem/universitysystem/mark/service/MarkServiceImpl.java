package uz.isystem.universitysystem.mark.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.mark.MarkDto;
import uz.isystem.universitysystem.mark.MarkMapper;
import uz.isystem.universitysystem.mark.MarkRepository;

import java.util.List;

@Service
public class MarkServiceImpl extends AbstractService<MarkMapper> implements MarkService{

    private final MarkRepository markRepository;

    public MarkServiceImpl(MarkRepository markRepository, MarkMapper markMapper) {
        super(markMapper);
        this.markRepository = markRepository;
    }

    @Override
    public MarkDto getById(Integer id) {
        return null;
    }

    @Override
    public void create(MarkDto dto) {

    }

    @Override
    public void update(MarkDto dto, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<MarkDto> getAll() {
        return null;
    }
}
