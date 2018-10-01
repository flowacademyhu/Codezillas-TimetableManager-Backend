package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.GroupService;
import hu.flowacademy.timetablemanager.service.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupResource {
    @Autowired
    private final GroupService groupService;

    public GroupResource(GroupService groupService){
        this.groupService = groupService;
    }

    // Return all 
    @GetMapping("/all")
    public ResponseEntity<List<GroupDTO>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    // Return a group by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.findOne(id));
    }

    // Create/edit group
    @PostMapping("/save")
    public ResponseEntity<GroupDTO> save(@RequestBody GroupDTO groupDTO) {
        return ResponseEntity.ok(groupService.save(groupDTO));
    }

    // Delete group
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }
}
