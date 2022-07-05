package uz.isystem.universitysystem.group;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.group.service.GroupService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity getGroup(@PathVariable("id") Integer groupId){
        GroupDto groupDto = groupService.getById(groupId);
        return ResponseEntity.ok(groupDto);
    }

    @PostMapping
    public ResponseEntity createGroup(@Valid @RequestBody GroupDto groupDto){
        groupService.create(groupDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/group").toString());
        return ResponseEntity.ok("NEW GROUP CREATED !");
    }

    @PutMapping("/{Id}")
    public ResponseEntity updateGroup(@PathVariable("id") Integer id,
                                      @Valid @RequestBody GroupDto groupDto){
        groupService.update(groupDto, id);
        return ResponseEntity.ok("Group Successfully updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") Integer id){
        groupService.delete(id);
        return ResponseEntity.ok("Group successfully deleted !");
    }

    @GetMapping
    public ResponseEntity getAllGroups(){
        List<GroupDto> groupDtoList = groupService.getAll();
        return ResponseEntity.ok(groupDtoList);
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<List<GroupDto>> getGroupsByFacultyId(@PathVariable("facultyId") Integer facultyId){
        List<GroupDto> groupDtoList = groupService.getGroupsByFacultyId(facultyId);
        return new ResponseEntity<>(groupDtoList, HttpStatus.OK);
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<GroupDto> getGroupsByJournalId(@PathVariable("journalId") Integer journalId){
        GroupDto groupDtoList = groupService.getGroupsByJournalId(journalId);
        return new ResponseEntity<>(groupDtoList, HttpStatus.OK);
    }
}
