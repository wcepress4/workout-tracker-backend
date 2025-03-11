package pumppro.springboot_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.WorkoutTemplateExerciseDto;
import pumppro.springboot_backend.service.WorkoutTemplateExerciseService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/workout-template-exercises")
public class WorkoutTemplateExerciseController {

    @Autowired
    private WorkoutTemplateExerciseService workoutTemplateExerciseService;

    @GetMapping
    public ResponseEntity<List<WorkoutTemplateExerciseDto>> getAllWorkoutTemplateExercises() {
        List<WorkoutTemplateExerciseDto> workoutTemplateExercises = workoutTemplateExerciseService.getAllWorkoutTemplateExercises();
        return ResponseEntity.ok(workoutTemplateExercises);
    }

    @PostMapping
    public ResponseEntity<WorkoutTemplateExerciseDto> createWorkoutTemplateExercise(@RequestBody WorkoutTemplateExerciseDto workoutTemplateExerciseDto) {
        WorkoutTemplateExerciseDto createdWorkoutTemplateExercise = workoutTemplateExerciseService.createWorkoutTemplateExercise(workoutTemplateExerciseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutTemplateExercise);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkoutTemplateExerciseDto> getWorkoutTemplateExerciseById(@PathVariable long id) {
        WorkoutTemplateExerciseDto workoutTemplateExercise = workoutTemplateExerciseService.getWorkoutTemplateExerciseById(id);
        return ResponseEntity.ok(workoutTemplateExercise);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkoutTemplateExerciseDto> updateWorkoutTemplateExercise(@PathVariable long id, @RequestBody WorkoutTemplateExerciseDto workoutTemplateExerciseDto) {
        WorkoutTemplateExerciseDto updatedWorkoutTemplateExercise = workoutTemplateExerciseService.updateWorkoutTemplateExercise(id, workoutTemplateExerciseDto);
        return ResponseEntity.ok(updatedWorkoutTemplateExercise);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkoutTemplateExercise(@PathVariable long id) {
        workoutTemplateExerciseService.deleteWorkoutTemplateExerciseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

