package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.config.SecurityConfig;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.GroupRepository;
import hu.flowacademy.timetablemanager.repository.RoleRepository;
import hu.flowacademy.timetablemanager.repository.UserRepository;
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
public class CustomUserDetailsService implements AuthService, UserDetailsService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private RoleRepository roleRepository;
    PasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, GroupRepository groupRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
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
    public boolean activateUser(User user, UserDTO userDTO) {
        if (userDTO.getActivationCode().equals(user.getActivationCode())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setName(userDTO.getName());
            user.setNickname(userDTO.getNickname());
            user.setActivationCode("");
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean createNewUser(String email, long groupId) {
        if (userRepository.findByEmail(email) != null) { return false; }
        try {
            User newUser = new User();
            newUser.setActivationCode(generateActivationCode());
            newUser.setEmail(email);
            newUser.setEnabled(false);
            newUser.setPassword(" ");
            newUser.setGroup(groupRepository.getOne(groupId));
            newUser.addRole(roleRepository.findByRole("USER"));
            userRepository.save(newUser);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    public String generateActivationCode() {
        return RandomStringUtils.randomAlphanumeric(24);
    }
}