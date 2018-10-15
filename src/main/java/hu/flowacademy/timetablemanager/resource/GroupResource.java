package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.GroupService;
import hu.flowacademy.timetablemanager.service.UserService;
import hu.flowacademy.timetablemanager.service.dto.GroupDTO;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupResource {

    private final GroupService groupService;

    private final UserService userService;

    public GroupResource(GroupService groupService, UserService userService){
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupDTO>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> findUsersByGroupId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findAllByGroupId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.findOne(id));
    }

    @PostMapping("/save")
    public ResponseEntity<GroupDTO> save(@RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.save(groupDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }
}
