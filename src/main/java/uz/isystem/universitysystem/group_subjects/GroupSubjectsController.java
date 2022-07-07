package uz.isystem.universitysystem.group_subjects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.universitysystem.group_subjects.service.GroupSubjectsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/group-subjects")
public class GroupSubjectsController {

    private final GroupSubjectsService groupSubjectsService;

    public GroupSubjectsController(GroupSubjectsService groupSubjectsService) {
        this.groupSubjectsService = groupSubjectsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupSubjectsDto> getGroupSubject(@PathVariable("id") Integer id){
        GroupSubjectsDto groupSubjectsDto = groupSubjectsService.getById(id);
        return new ResponseEntity(groupSubjectsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createGroupSubjects(@Valid @RequestBody GroupSubjectsDto groupSubjectsDto){
        groupSubjectsService.create(groupSubjectsDto);
        return new ResponseEntity("New Group Subject Created Successfully !", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroupSubjectsDto>> getAllGroupSubjects(){
        List<GroupSubjectsDto> groupSubjectsDtoList = groupSubjectsService.getAll();
        return new ResponseEntity<>(groupSubjectsDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroupSubjects(@PathVariable("id") Integer id,
                                                    @Valid @RequestBody GroupSubjectsDto groupSubjectsDto){
        groupSubjectsService.update(groupSubjectsDto, id);
        return new ResponseEntity("Group Subject Updated Successfully !", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateGroupSubject(@PathVariable("id") Integer id){
        groupSubjectsService.delete(id);
        return new ResponseEntity("Group Subject Deleted Successfully !", HttpStatus.OK);
    }
}
