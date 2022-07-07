package uz.isystem.universitysystem.faculty.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.dto.FacultyGroupsDto;
import uz.isystem.universitysystem.faculty.Faculty;
import uz.isystem.universitysystem.faculty.FacultyDto;

@Service
public interface FacultyService extends GenericCrudService<Faculty, FacultyDto, Integer> {

    void existFaculty(Integer facultyId);

    FacultyGroupsDto getGroupsOfFaculty(Integer facultyId);

}
