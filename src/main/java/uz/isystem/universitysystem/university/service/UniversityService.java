package uz.isystem.universitysystem.university.service;

import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.university.University;
import uz.isystem.universitysystem.university.UniversityDto;

public interface UniversityService extends GenericCrudService<University, UniversityDto, Integer> {

    boolean isExistUniversity(Integer id);

}
