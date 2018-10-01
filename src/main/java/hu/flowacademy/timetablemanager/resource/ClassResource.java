package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.ClassService;
import hu.flowacademy.timetablemanager.service.dto.ClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClassResource {
    @Autowired
    private final ClassService classService;

    public ClassResource(ClassService classService){
        this.classService = classService;
    }

    // Return all classes
    @GetMapping("/all")
    public ResponseEntity<List<ClassDTO>> findAll() {
        return ResponseEntity.ok(classService.findAll());
    }

    // Return a class by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(classService.findOne(id));
    }

    // Create/edit class
    @PostMapping("/save")
    public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO classDTO) {
        return ResponseEntity.ok(classService.save(classDTO));
    }

    // Delete class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classService.delete(id);
        return ResponseEntity.ok().build();
    }
}
