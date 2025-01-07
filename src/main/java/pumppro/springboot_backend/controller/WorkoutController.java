package pumppro.springboot_backend.controller;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.ExerciseDto;
import pumppro.springboot_backend.dtos.WorkoutDto;
import pumppro.springboot_backend.model.Workout;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.WorkoutRepository;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.exception.ResourceNotFoundException;
import pumppro.springboot_backend.service.ExerciseService;
import pumppro.springboot_backend.service.WorkoutService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    // build get all sets REST API
    @GetMapping
    public ResponseEntity<List<WorkoutDto>> getAllWorkouts() {
        List<WorkoutDto> workouts = workoutService.getAllWorkouts();
        return ResponseEntity.ok(workouts);
    }

    // build create exercise REST API
    @PostMapping
    public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutDto workoutDto) {
        WorkoutDto createdWorkout = workoutService.createWorkout(workoutDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkout);
    }

    // build get exercise by id REST API
    @GetMapping("{id}")
    public ResponseEntity<WorkoutDto> getWorkoutById(@PathVariable long id) {
        WorkoutDto workout = workoutService.getWorkoutById(id);
        return ResponseEntity.ok(workout);
    }

    // build update exercise REST API
    @PutMapping("{id}")
    public ResponseEntity<WorkoutDto> updateWorkout(@PathVariable long id, @RequestBody WorkoutDto workoutDto) {
        WorkoutDto updatedWorkout = workoutService.updateWorkout(id, workoutDto);
        return ResponseEntity.ok(updatedWorkout);
    }

    // build delete exercise REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWorkout(@PathVariable long id) {
        workoutService.deleteWorkout(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
