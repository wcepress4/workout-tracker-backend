package pumppro.springboot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.WorkoutSetDto;
import pumppro.springboot_backend.service.WorkoutSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/workout-sets")
public class WorkoutSetController {

    @Autowired
    private WorkoutSetService workoutSetService;

    @GetMapping
    public ResponseEntity<List<WorkoutSetDto>> getAllWorkoutSets() {
        List<WorkoutSetDto> workoutSets = workoutSetService.getAllWorkoutSets();
        return ResponseEntity.ok(workoutSets);
    }

    @PostMapping
    public ResponseEntity<WorkoutSetDto> createWorkoutSet(@RequestBody WorkoutSetDto workoutSetDto) {
        WorkoutSetDto createdWorkoutSet = workoutSetService.createWorkoutSet(workoutSetDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutSet);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutSetDto> getWorkoutSetById(@PathVariable long id) {
        WorkoutSetDto workoutSet = workoutSetService.getWorkoutSetById(id);
        return ResponseEntity.ok(workoutSet);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkoutSetDto> updateWorkoutSet(@PathVariable long id, @RequestBody WorkoutSetDto workoutSetDto) {
        WorkoutSetDto updatedWorkoutSet = workoutSetService.updateWorkoutSet(id, workoutSetDto);
        return ResponseEntity.ok(updatedWorkoutSet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkoutSet(@PathVariable long id) {
        workoutSetService.deleteWorkoutSet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
