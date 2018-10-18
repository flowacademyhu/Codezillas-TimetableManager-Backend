package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.Class;
import hu.flowacademy.timetablemanager.model.Role;
import hu.flowacademy.timetablemanager.model.Subject;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.RoleRepository;
import hu.flowacademy.timetablemanager.repository.SubjectRepository;
import hu.flowacademy.timetablemanager.service.dto.RoleDTO;
import hu.flowacademy.timetablemanager.service.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    @Autowired
    private UserService userService;

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDTO save(RoleDTO roleDTO) {
        Role entity = toEntity(roleDTO);
        return toDto(roleRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return toDto(roleRepository.findAll());
    }

    @Transactional(readOnly = true)
    public RoleDTO findOne(Long id) {
        return roleRepository.findById(id)
                .map(this::toDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        return toDto(roleRepository.findByName(name));
    }

    public Role findOneDirectByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Role findOneDirect(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public List<RoleDTO> toDto(List<Role> roles) {
        return roles.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    private RoleDTO toDto(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setUserIds(role.getUsers()
                .stream().map(User::getId)
                .collect(Collectors.toList()));

        return roleDTO;
    }

    private Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setUsers(roleDTO.getUserIds()
                .stream().map(userId -> userService.findOneDirect(userId))
                .collect(Collectors.toList()));

        return role;
    }
}
