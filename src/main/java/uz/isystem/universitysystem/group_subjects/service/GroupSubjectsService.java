package uz.isystem.universitysystem.group_subjects.service;

import uz.isystem.universitysystem._service.GenericCrudService;
import uz.isystem.universitysystem.dto.SubjectsDto;
import uz.isystem.universitysystem.group_subjects.GroupSubjects;
import uz.isystem.universitysystem.group_subjects.GroupSubjectsDto;

public interface GroupSubjectsService extends GenericCrudService<GroupSubjects, GroupSubjectsDto, Integer> {

    public SubjectsDto getSubjectsByGroupId(Integer groupId);

}
