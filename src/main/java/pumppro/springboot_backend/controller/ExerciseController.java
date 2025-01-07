package pumppro.springboot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.dtos.ExerciseDto;
import pumppro.springboot_backend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises(@RequestParam Long userId, @RequestParam(required = false) String bodyPart, @RequestParam(required = false) String category) {
        List<ExerciseDto> exercises = exerciseService.getAllExercises(userId, bodyPart, category);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/personal")
    public ResponseEntity<List<ExerciseDto>> getAllExercisesPersonal(@RequestParam Long userId, @RequestParam(required = false) String bodyPart, @RequestParam(required = false) String category) {
        List<ExerciseDto> exercises = exerciseService.getAllExercisesPersonal(userId, bodyPart, category);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/default")
    public ResponseEntity<List<ExerciseDto>> getAllExercisesDefault(@RequestParam(required = false) String bodyPart, @RequestParam(required = false) String category) {
        List<ExerciseDto> exercises = exerciseService.getAllExercisesDefault(bodyPart, category);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ExerciseDto>> getAllExercisesAdmin(@RequestParam(required = false) String bodyPart, @RequestParam(required = false) String category) {
        List<ExerciseDto> exercises = exerciseService.getAllExercisesAdmin(bodyPart, category);
        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        ExerciseDto createdExercise = exerciseService.createExercise(exerciseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable long id) {
        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ExerciseDto> getExerciseByName(@PathVariable String name) {
        ExerciseDto exercise = exerciseService.getExerciseByName(name);
        return ResponseEntity.ok(exercise);
    }
    @GetMapping("/{id}/admin-status")
    public ResponseEntity<Boolean> getAdminStatusByExerciseCreator(@PathVariable Long id) {
        boolean isAdmin = exerciseService.getAdminStatusByExerciseCreator(id);
        return ResponseEntity.ok(isAdmin);
    }

    @PutMapping("{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable long id, @RequestBody ExerciseDto exerciseDto) {
        ExerciseDto updatedExercise = exerciseService.updateExercise(id, exerciseDto);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteExercise(@PathVariable long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
