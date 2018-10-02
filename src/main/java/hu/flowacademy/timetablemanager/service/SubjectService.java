package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Subject;
import hu.flowacademy.timetablemanager.repository.SubjectRepository;
import hu.flowacademy.timetablemanager.service.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> subjects = new ArrayList<>();

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
        return subjects.stream().map((subject) -> {
            return toDto(subject);
        })
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
        return subject;
    }
}
