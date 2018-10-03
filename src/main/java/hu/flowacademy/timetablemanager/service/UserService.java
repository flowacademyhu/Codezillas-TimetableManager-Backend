package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.UserRepository;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO save(UserDTO userDTO) {
        User entity = toEntity(userDTO);
        return toDto(userRepository.save(entity));
    }

    public List<UserDTO> findAll() {
        return toDto(userRepository.findAll());
    }

    public UserDTO findOne(Long id) {
        return userRepository.findById(id)
                .map(this::toDto).orElse(null);
    }

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
        // userDTO.setRoles(user.getRoles());
        userDTO.setName(user.getName());
        userDTO.setNickname(user.getNickname());
        userDTO.setGroupID(user.getGroup().getId());
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
        // user.setRoles(userDTO.getRoles());
        user.setName(userDTO.getName());
        user.setNickname(userDTO.getNickname());
        // user.setGroup(userDTO.getGroupID());
        return user;
    }
}
