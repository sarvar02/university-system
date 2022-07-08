package uz.isystem.universitysystem.group;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.isystem.universitysystem.dto.FacultyGroupsDto;
import uz.isystem.universitysystem.dto.GroupRatingDto;
import uz.isystem.universitysystem.dto.StudentRatingsDto;
import uz.isystem.universitysystem.group.service.GroupService;
import uz.isystem.universitysystem.student.Student;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable("id") Integer id){
        GroupDto groupDto = groupService.getById(id);
        return new ResponseEntity(groupDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@Valid @RequestBody GroupDto groupDto){
        groupService.create(groupDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/group").toString());
        return new ResponseEntity("NEW GROUP CREATED !", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable("id") Integer id,
                                      @Valid @RequestBody GroupDto groupDto){
        groupService.update(groupDto, id);
        return new ResponseEntity("Group Successfully updated !", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Integer id){
        groupService.delete(id);
        return new ResponseEntity("Group successfully deleted !", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups(){
        List<GroupDto> groupDtoList = groupService.getAll();
        return new ResponseEntity(groupDtoList, HttpStatus.OK);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<GroupDto>> getGroupsByFacultyId(@PathVariable("facultyId") Integer facultyId){
        List<GroupDto> groupDtoList = groupService.getGroupsByFacultyId(facultyId);
        return new ResponseEntity<>(groupDtoList, HttpStatus.OK);
    }

    @GetMapping("/journal/{journalId}")
    public ResponseEntity<GroupDto> getGroupsByJournalId(@PathVariable("journalId") Integer journalId){
        GroupDto groupDtoList = groupService.getGroupsByJournalId(journalId);
        return new ResponseEntity<>(groupDtoList, HttpStatus.OK);
    }

    // Studentlarni olgan bahosi bo'yicha kamayish tartibida chiqarish
    @GetMapping("/{groupId}/studentRatings")
    @ApiOperation(value = "Get the list of student with their marks in descending order",
            notes = "Provide a group id to look up specific student from database",
            response = GroupRatingDto.class)
    public ResponseEntity<GroupRatingDto> getStudentsRatingByGroupId(@PathVariable("groupId") Integer groupId){
        GroupRatingDto ratingsDtoList = groupService.getStudentsMarkByGroupId(groupId);
        return new ResponseEntity<>(ratingsDtoList, HttpStatus.OK);
    }
}