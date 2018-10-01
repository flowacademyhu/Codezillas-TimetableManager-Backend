package hu.flowacademy.timetablemanager.resource;

import hu.flowacademy.timetablemanager.service.SubjectService;
import hu.flowacademy.timetablemanager.service.dto.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectResource {
    @Autowired
    private final SubjectService subjectService;

    public SubjectResource(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    // Return all subjects
    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> findAll() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    // Return a subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.findOne(id));
    }

    // Create/edit subject
    @PostMapping("/save")
    public ResponseEntity<SubjectDTO> save(@RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.save(subjectDTO));
    }

    // Delete subject
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
