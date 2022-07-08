package uz.isystem.universitysystem.mark.service;

import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.mark.Mark;
import uz.isystem.universitysystem.mark.MarkDto;

import java.util.List;

public interface MarkService extends GenericCrudService<Mark, MarkDto, Integer> {

    public List<Mark> getAllMarksOrderByMarkValue();

}
