package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.UserService;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {
    @Autowired
    private final UserService userService;

    public UserResource(UserService userService){
        this.userService = userService;
    }

    // Return all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Return a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    // Create/edit user
    @PostMapping("/save")
    public ResponseEntity<UserDTO> save() {
        return ResponseEntity.ok(userService.save(UserDTO));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
