package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Subject;
import hu.flowacademy.timetablemanager.repository.SubjectRepository;
import hu.flowacademy.timetablemanager.service.dto.SubjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectService {

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
        subjectDTO.setClasses(subject.getClasses());
        subjectDTO.setUsers(subject.getUsers());
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
        subject.setClasses(subjectDTO.getClasses());
        subject.setUsers(subjectDTO.getUsers());
        return subject;
    }
}
