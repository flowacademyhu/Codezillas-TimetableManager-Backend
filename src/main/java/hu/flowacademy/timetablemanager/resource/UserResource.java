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

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<UserDTO>> save(@RequestBody List<UserDTO> userDTOS) {
        return ResponseEntity.ok(userService.save(userDTOS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
