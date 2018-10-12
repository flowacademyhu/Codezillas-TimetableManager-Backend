package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.UserRepository;
import hu.flowacademy.timetablemanager.service.AuthService;
import hu.flowacademy.timetablemanager.service.CustomUserDetailsService;
import hu.flowacademy.timetablemanager.service.EmailService;
import hu.flowacademy.timetablemanager.service.UserService;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EntryResource {
    private UserRepository userRepository;
    private UserService userService;
    private AuthService authService;
    private CustomUserDetailsService customUDS;
    private EmailService emailService;

    @Autowired
    public EntryResource(UserRepository userRepository, UserService userService,
                         CustomUserDetailsService customUserDetailsService,
                         AuthService authService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.customUDS = customUserDetailsService;
        this.authService = authService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "/";
    }

    @PostMapping("/createUser")
    public boolean createUser(@RequestBody Map<String, String> json) {
        String withEmail = json.get("email");
        long withGroupId = Long.parseLong(json.get("groupId"));
        return customUDS.createNewUser(withEmail, withGroupId);
    }

    @PostMapping("/sendActivatorMail")
    public boolean sendActivator(@RequestBody Map<String, String> json) {
        String emailTo = json.get("emailTo");
        return emailService.sendMessage(emailTo);
    }

    @PostMapping("/registration")
    public boolean registration(@RequestBody UserDTO userDTO) {
        User newUser = userRepository.findByEmail(userDTO.getEmail());
        if (newUser == null) {
            return false;
        } else {
            return authService.activateUser(newUser, userDTO);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginGet() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return new ResponseEntity<>(
                    new UserDTO(),
                    HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> profile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO resultDTO = userService.toDto(userRepository.findByEmail(email));
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        return new ResponseEntity<UserDTO>(
                resultDTO,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "This is the ADMIN-PAGE!";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }
}
