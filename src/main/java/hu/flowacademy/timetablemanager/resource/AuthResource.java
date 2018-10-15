package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.AuthService;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
//@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AuthResource {
    private final AuthService authService;

    @Autowired
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String root() {
        return "/";
    }

    @PostMapping("/addactiveuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = authService.addNewActiveUser(userDTO);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/createuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody Map<String, String> json) {
        UserDTO newUser = authService.createNewUser(json);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/sendmail")
    public ResponseEntity<Void> sendActivator(@RequestBody Map<String, String> json) {
        if (authService.sendMail(json)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(408).build();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDTO) {
        UserDTO newUser = authService.activateUser(userDTO);
        if (newUser != null) {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile(HttpServletRequest request) {
        UserDTO actualUser = authService.getCurrentUser();
        if (actualUser != null) {
            return new ResponseEntity<>(actualUser, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok().build();
    }
}
