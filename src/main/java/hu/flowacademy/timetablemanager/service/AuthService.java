package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.config.SecurityConfig;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final CustomUserDetailsService customUDS;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public AuthService(CustomUserDetailsService customUDS, EmailService emailService, UserService userService) {
        this.customUDS = customUDS;
        this.emailService = emailService;
        this.userService = userService;
    }

    public UserDTO createNewUser(Map<String, String> json) {
        String withEmail = json.get("email");
        long withGroupId = Long.parseLong(json.get("groupId"));
        return customUDS.createNewUser(withEmail, withGroupId);
    }

    public UserDTO activateUser(UserDTO userDTO) {
        return customUDS.activateUser(userDTO);
    }

    public boolean sendMail(Map<String, String> json) {
        String emailTo = json.get("emailTo");
        return emailService.sendMessage(emailTo);
    }

    public UserDTO getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User actualUser = customUDS.findByEmail(email);
        return userService.findOne(actualUser.getId());
    }

    public UserDTO addNewActiveUser(UserDTO userDTO) {
        UserDTO newUser = userDTO;
        newUser.setEnabled(true);
        newUser.setActivationCode("");
        newUser.setPassword(SecurityConfig.passwordEncoder.encode(userDTO.getPassword()));
        return newUser;
    }
}
