package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.repository.ClassRepository;
import hu.flowacademy.timetablemanager.service.dto.ClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> classes = new ArrayList<>();

    public ClassDTO save(ClassDTO classDTO) {
        Class entity = toEntity(classDTO);
        return toDto(classRepository.save(entity));
    }

    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    public ClassDTO findOne(Long id) {
        return classRepository.findById(id)
                .map(this::toDto).orElse(null);
    }

    public List<ClassDTO> findAll() {
        return toDto(classRepository.findAll());
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
        classDTO.setFk_id_group(cls.getGroup().getId());
        classDTO.setFk_id_subject(cls.getSubject().getId());
        classDTO.setFk_id_mentor_many_to_many(cls.getUsers().get(0).getId());
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
/*        cls.setGroup(classDTO.getFk_id_group());
        cls.setSubject();
        cls.setUsers();*/
        return cls;
        }

}
