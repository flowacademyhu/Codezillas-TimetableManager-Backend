package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.*;
import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.repository.ClassRepository;
import hu.flowacademy.timetablemanager.repository.GroupRepository;
import hu.flowacademy.timetablemanager.repository.SubjectRepository;
import hu.flowacademy.timetablemanager.repository.UserRepository;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SubjectService subjectService;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO save(UserDTO userDTO) {
        User entity = toEntity(userDTO);
        return toDto(userRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return toDto(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAllByGroupId(Long id) {
        return toDto(userRepository.findByGroupId(id));
    }

    @Transactional(readOnly = true)
    public UserDTO findOne(Long id) {
        return userRepository.findById(id)
                .map(this::toDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public User findOneDirect(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setNickname(user.getNickname());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setActivationCode(user.getActivationCode());
        userDTO.setGroupId(Optional.ofNullable(user.getGroup())
                .map(Group::getId).orElse(null));
        userDTO.setClassIds(user.getClasses()
                .stream().map(Class::getId)
                .collect(Collectors.toList()));
        userDTO.setSubjectIds(user.getSubjects()
                .stream().map(Subject::getId)
                .collect(Collectors.toList()));
        userDTO.setRoles(user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()));
        return userDTO;
    }

    private User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();

        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setNickname(userDTO.getNickname());
        user.setEnabled(userDTO.isEnabled());
        user.setActivationCode(userDTO.getActivationCode());
        user.setClasses(userDTO.getClassIds()
                .stream().map(classId -> classService.findOneDirect(classId))
                .collect(Collectors.toList()));
        user.setSubjects(userDTO.getSubjectIds()
                .stream().map(subjectId -> subjectService.findOneDirect(subjectId))
                .collect(Collectors.toList()));
        user.setGroup(groupService.findOneDirect(userDTO.getGroupId()));
        //user.setRoles(user.getRoles());
        return user;
    }
}
