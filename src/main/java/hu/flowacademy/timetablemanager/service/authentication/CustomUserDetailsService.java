package hu.flowacademy.timetablemanager.service.authentication;

import hu.flowacademy.timetablemanager.config.SecurityConfig;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.GroupRepository;
import hu.flowacademy.timetablemanager.repository.RoleRepository;
import hu.flowacademy.timetablemanager.repository.UserRepository;
import hu.flowacademy.timetablemanager.service.UserService;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements IServiceForAuth, UserDetailsService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private RoleRepository roleRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, GroupRepository groupRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO activateUser(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (userDTO.getActivationCode().equals(user.getActivationCode())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setName(userDTO.getName());
            user.setNickname(userDTO.getNickname());
            user.setActivationCode("");
            user.setEnabled(true);
            userRepository.save(user);
            return userService.findOne(user.getId());
        }
        return null;
    }

    @Override
    public UserDTO createNewUser(String email, long groupId) {
        UserDTO newDto = new UserDTO();
        if (userRepository.findByEmail(email) != null) { return newDto; }
        try {
            User newUser = adjustUserProperties(email, groupId);
            userRepository.save(newUser);
            newDto = userService.findOne(newUser.getId());
        } catch (Exception e) {
            throw e;
        }
        return newDto;
    }

    private User adjustUserProperties(String email, long groupId) {
        User result = new User();
        result.setActivationCode(generateActivationCode());
        result.setEmail(email);
        result.setEnabled(false);
        result.setPassword(" ");
        result.setGroup(groupRepository.getOne(groupId));
        result.addRole(roleRepository.findByName("USER"));
        return result;
    }

    private String generateActivationCode() {
        return RandomStringUtils.randomAlphanumeric(24);
    }
}
