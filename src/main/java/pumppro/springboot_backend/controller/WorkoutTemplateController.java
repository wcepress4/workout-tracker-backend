package pumppro.springboot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.WorkoutTemplateDto;
import pumppro.springboot_backend.service.WorkoutTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/workout-templates")
public class WorkoutTemplateController {

    @Autowired
    private WorkoutTemplateService workoutTemplateService;

    @GetMapping
    public ResponseEntity<List<WorkoutTemplateDto>> getAllWorkoutTemplates(@RequestParam Long userId) {
        List<WorkoutTemplateDto> templates = workoutTemplateService.getAllWorkoutTemplates(userId);
        return ResponseEntity.ok(templates);
    }

    @PostMapping
    public ResponseEntity<WorkoutTemplateDto> createWorkoutTemplate(@RequestBody WorkoutTemplateDto workoutTemplateDto) {
        WorkoutTemplateDto createdTemplate = workoutTemplateService.createWorkoutTemplate(workoutTemplateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTemplate);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutTemplateDto> getWorkoutTemplateById(@PathVariable long id) {
        WorkoutTemplateDto template = workoutTemplateService.getWorkoutTemplateById(id);
        return ResponseEntity.ok(template);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkoutTemplate(@PathVariable long id) {
        workoutTemplateService.deleteWorkoutTemplate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
