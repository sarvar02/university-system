package uz.isystem.universitysystem.student.service;

import org.springframework.stereotype.Service;
import uz.isystem.universitysystem._service.AbstractService;
import uz.isystem.universitysystem.dto.StudentInfoDto;
import uz.isystem.universitysystem.dto.StudentSubjectsDto;
import uz.isystem.universitysystem.dto.SubjectsDto;
import uz.isystem.universitysystem.exception.NotFoundException;
import uz.isystem.universitysystem.faculty.FacultyDto;
import uz.isystem.universitysystem.faculty.service.FacultyService;
import uz.isystem.universitysystem.group.GroupDto;
import uz.isystem.universitysystem.group.service.GroupService;
import uz.isystem.universitysystem.group_subjects.service.GroupSubjectsService;
import uz.isystem.universitysystem.student.Student;
import uz.isystem.universitysystem.student.StudentDto;
import uz.isystem.universitysystem.student.StudentMapper;
import uz.isystem.universitysystem.student.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl extends AbstractService<StudentMapper> implements StudentService{

    private final StudentRepository studentRepository;
    private final GroupService groupService;
    private final GroupSubjectsService groupSubjectsService;
    private final FacultyService facultyService;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, GroupService groupService, GroupSubjectsService groupSubjectsService, FacultyService facultyService) {
        super(studentMapper);
        this.studentRepository = studentRepository;
        this.groupService = groupService;
        this.groupSubjectsService = groupSubjectsService;
        this.facultyService = facultyService;
    }

    @Override
    public StudentDto getById(Integer id) {
        return mapper.toDto(getEntity(id));
    }

    @Override
    public void create(StudentDto dto) {

        // Checking group
        groupService.existGroup(dto.getGroupId());

        Student student = mapper.toEntity(dto);
        student.setCreatedDate(LocalDateTime.now());
        student.setIsActive(true);

        // Save to Database
        saveToDatabase(student);
    }

    @Override
    public void update(StudentDto dto, Integer id) {
        Student student = getEntity(id);

        Student newStudent = mapper.toEntity(dto);
        newStudent.setStudentId(id);
        newStudent.setCreatedDate(student.getCreatedDate());
        newStudent.setIsActive(student.getIsActive());
        newStudent.setUpdatedDate(LocalDateTime.now());

        saveToDatabase(newStudent);
    }

    @Override
    public void delete(Integer id) {

        Student student = getEntity(id);
        student.setDeletedDate(LocalDateTime.now());

        saveToDatabase(student);
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> studentList = getAllEntities();
        return mapper.toDto(studentList);
    }

    @Override
    public List<StudentDto> getStudentsByGroupId(Integer groupId) {
        List<Student> studentList = studentRepository.findAllByGroupIdAndDeletedDateIsNullAndIsActive(groupId, true);
        if(studentList.isEmpty())
            throw new NotFoundException("Students not found in this group !");
        return mapper.toDto(studentList);
    }

    @Override
    public StudentSubjectsDto getSubjectsOfStudent(Integer studentId) {
        StudentDto studentDto = getById(studentId);
        SubjectsDto subjectsDto = groupSubjectsService.getSubjectsByGroupId(studentDto.getGroupId());

        return new StudentSubjectsDto(studentDto, subjectsDto);
    }

    @Override
    public StudentInfoDto getStudentInfoById(Integer studentId) {
        StudentDto studentDto = getById(studentId);
        GroupDto groupDto = groupService.getById(studentDto.getGroupId());
        FacultyDto facultyDto = facultyService.getById(groupDto.getFacultyId());

        return new StudentInfoDto(studentDto.getName(), groupDto.getName(), facultyDto.getFacultyName());
    }


    // ======== SECONDARY FUNCTIONS ========

    public Student getEntity(Integer studentId){
        return studentRepository.findByStudentIdAndIsActiveAndDeletedDateIsNull(studentId, true)
                .orElseThrow(() -> new NotFoundException("Student not found !"));
    }

    public List<Student> getAllEntities(){
        List<Student> studentList = studentRepository.findAllByDeletedDateIsNullAndIsActive(true);
        if(studentList.isEmpty())
            throw new NotFoundException("Student not found !");
        return studentList;
    }

    public void saveToDatabase(Student student){
        studentRepository.save(student);
    }

    public void existStudent(Integer studentId){
        if(!studentRepository.existsByStudentIdAndDeletedDateIsNullAndIsActive(studentId, true))
            throw new NotFoundException("Student with ID not found !");
    }

}
