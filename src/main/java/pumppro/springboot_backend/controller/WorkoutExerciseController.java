package pumppro.springboot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.WorkoutExerciseDto;
import pumppro.springboot_backend.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/workout-exercises")
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    @GetMapping
    public ResponseEntity<List<WorkoutExerciseDto>> getAllWorkoutExercises() {
        List<WorkoutExerciseDto> workoutExercises = workoutExerciseService.getAllWorkoutExercises();
        return ResponseEntity.ok(workoutExercises);
    }

    @PostMapping
    public ResponseEntity<WorkoutExerciseDto> createWorkoutExercise(@RequestBody WorkoutExerciseDto workoutExerciseDto) {
        WorkoutExerciseDto createdWorkoutExercise = workoutExerciseService.createWorkoutExercise(workoutExerciseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutExercise);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutExerciseDto> getWorkoutExerciseById(@PathVariable long id) {
        WorkoutExerciseDto workoutExercise = workoutExerciseService.getWorkoutExerciseById(id);
        return ResponseEntity.ok(workoutExercise);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkoutExerciseDto> updateWorkoutExercise(@PathVariable long id, @RequestBody WorkoutExerciseDto workoutExerciseDto) {
        WorkoutExerciseDto updatedWorkoutExercise = workoutExerciseService.updateWorkoutExercise(id, workoutExerciseDto);
        return ResponseEntity.ok(updatedWorkoutExercise);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkoutExercise(@PathVariable long id) {
        workoutExerciseService.deleteWorkoutExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
