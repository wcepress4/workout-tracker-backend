package pumppro.springboot_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.WorkoutTemplateSetDto;
import pumppro.springboot_backend.service.WorkoutTemplateSetService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/workout-template-sets")
public class WorkoutTemplateSetController {

    @Autowired
    private WorkoutTemplateSetService workoutTemplateSetService;

    @GetMapping
    public ResponseEntity<List<WorkoutTemplateSetDto>> getAllWorkoutTemplateSets() {
        List<WorkoutTemplateSetDto> workoutTemplateSets = workoutTemplateSetService.getAllWorkoutTemplateSets();
        return ResponseEntity.ok(workoutTemplateSets);
    }

    @PostMapping
    public ResponseEntity<WorkoutTemplateSetDto> createWorkoutTemplateSet(@RequestBody WorkoutTemplateSetDto workoutTemplateSetDto) {
        WorkoutTemplateSetDto createdWorkoutTemplateSet = workoutTemplateSetService.createWorkoutTemplateSet(workoutTemplateSetDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutTemplateSet);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutTemplateSetDto> getWorkoutTemplateSetById(@PathVariable long id) {
        WorkoutTemplateSetDto workoutTemplateSet = workoutTemplateSetService.getWorkoutTemplateSetById(id);
        return ResponseEntity.ok(workoutTemplateSet);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkoutTemplateSetDto> updateWorkoutTemplateSet(@PathVariable long id, @RequestBody WorkoutTemplateSetDto workoutTemplateSetDto) {
        WorkoutTemplateSetDto updatedWorkoutTemplateSet = workoutTemplateSetService.updateWorkoutTemplateSet(id, workoutTemplateSetDto);
        return ResponseEntity.ok(updatedWorkoutTemplateSet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkoutTemplateSet(@PathVariable long id) {
        workoutTemplateSetService.deleteWorkoutTemplateSetById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
