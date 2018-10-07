package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Subject;
import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.SubjectRepository;
import hu.flowacademy.timetablemanager.service.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectService {

    @Autowired
    UserService userService;

    @Autowired
    ClassService classService;

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectDTO save(SubjectDTO subjectDTO) {
        Subject entity = toEntity(subjectDTO);
        return toDto(subjectRepository.save(entity));
    }

    public List<SubjectDTO> findAll() {
        return toDto(subjectRepository.findAll());
    }

    public SubjectDTO findOne(Long id) {
        return subjectRepository.findById(id)
                .map(subject -> toDto(subject)).orElse(null);
    }

    public Subject findOneDirect(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<SubjectDTO> toDto(List<Subject> subjects) {
        return subjects.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    private SubjectDTO toDto(Subject subject) {
        if (subject == null) {
            return null;
        }
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setTitle(subject.getTitle());
        subjectDTO.setColor(subject.getColor());
        subjectDTO.setClassIds(subject.getClasses()
                .stream().map(Class::getId)
                .collect(Collectors.toList()));
        subjectDTO.setUserIds(subject.getUsers()
                .stream().map(User::getId)
                .collect(Collectors.toList()));
        return subjectDTO;
    }

    private Subject toEntity(SubjectDTO subjectDTO) {
        if (subjectDTO == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setTitle(subjectDTO.getTitle());
        subject.setColor(subjectDTO.getColor());
        subject.setClasses(subjectDTO.getClassIds()
            .stream().map(classId -> classService.findOneDirect(classId))
                .collect(Collectors.toList()));
        subject.setUsers(subjectDTO.getUserIds()
                .stream().map(userId -> userService.findOneDirect(userId))
                .collect(Collectors.toList()));
        return subject;
    }
}
