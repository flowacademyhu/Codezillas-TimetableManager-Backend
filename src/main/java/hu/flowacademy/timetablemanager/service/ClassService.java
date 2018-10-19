package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.Group;
import hu.flowacademy.timetablemanager.model.Subject;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.ClassRepository;
import hu.flowacademy.timetablemanager.service.dto.ClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassService {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public ClassDTO save(ClassDTO classDTO) {
        Class entity = toEntity(classDTO);
        return toDto(classRepository.save(entity));
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ClassDTO findOne(Long id) {
        return classRepository.findById(id)
                .map(this::toDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public Class findOneDirect(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ClassDTO> findAll() {
        return toDto(classRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<ClassDTO> filter(Long startDateStart, Long startDateEnd) {
        return toDto(classRepository.filter(startDateStart, startDateEnd));
    }

    @Transactional(readOnly = true)
    public List<ClassDTO> filter(Long groupId, Long startDateStart, Long startDateEnd) {
        return toDto(classRepository.filter(groupId, startDateStart, startDateEnd));
    }

    @Transactional(readOnly = true)
    public List<ClassDTO> filter(Long userId, Long groupId, Long startDateStart, Long startDateEnd) {
        List<ClassDTO> result;

        if(groupId != null){
            result = filter(groupId, startDateStart, startDateEnd);
        } else {
            result = filter(startDateStart,  startDateEnd);
        }

        if(userId != null) {
            result = result.stream().filter(o -> o.getMentorIds().contains(userId)).collect(Collectors.toList());
        }

        return result;
    }

    private List<ClassDTO> toDto(List<Class> classes) {
        return classes.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ClassDTO toDto(Class cls) {
        if (cls == null) {
            return null;
        }

        ClassDTO classDTO = new ClassDTO();

        classDTO.setId(cls.getId());
        classDTO.setStartDate(cls.getStartDate());
        classDTO.setEndDate(cls.getEndDate());
        classDTO.setComment(cls.getComment());
        classDTO.setGroupId(Optional.ofNullable(cls.getGroup())
                .map(Group::getId).orElse(null));
        classDTO.setSubjectId(Optional.ofNullable(cls.getSubject())
            .map(Subject::getId).orElse(null));
        classDTO.setMentorIds(cls.getUsers()
                .stream().map(User::getId)
                .collect(Collectors.toList()));

        return classDTO;
    }

    private Class toEntity(ClassDTO classDTO) {
        if (classDTO == null) {
            return null;
        }

        Class cls = new Class();

        cls.setId(classDTO.getId());
        cls.setStartDate(classDTO.getStartDate());
        cls.setEndDate(classDTO.getEndDate());
        cls.setComment(classDTO.getComment());
        cls.setGroup(groupService.findOneDirect(classDTO.getGroupId()));
        cls.setSubject(subjectService.findOneDirect(classDTO.getSubjectId()));
        cls.setUsers(classDTO.getMentorIds()
                .stream().map(mentorId -> userService.findOneDirect(mentorId))
                .collect(Collectors.toList()));

        return cls;
        }

}
