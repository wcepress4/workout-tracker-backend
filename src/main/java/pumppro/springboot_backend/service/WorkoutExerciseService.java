package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutExerciseDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.WorkoutExerciseMapper;
import pumppro.springboot_backend.model.WorkoutExercise;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.WorkoutExerciseRepository;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.repository.WorkoutRepository;
import pumppro.springboot_backend.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseMapper workoutExerciseMapper;

    public WorkoutExerciseDto createWorkoutExercise(WorkoutExerciseDto workoutExerciseDto) {
        User user = userRepository.findById(workoutExerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        WorkoutExercise workoutExercise = workoutExerciseMapper.toWorkoutExercise(workoutExerciseDto);
        workoutExercise.setCreatedBy(user);

        WorkoutExercise savedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
        return workoutExerciseMapper.toWorkoutExerciseDto(savedWorkoutExercise);
    }

    public List<WorkoutExerciseDto> getAllWorkoutExercises() {
        return workoutExerciseRepository.findAll().stream()
                .map(workoutExerciseMapper::toWorkoutExerciseDto)
                .collect(Collectors.toList());
    }

    public WorkoutExerciseDto getWorkoutExerciseById(long id) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutExercise not found", HttpStatus.NOT_FOUND));
        return workoutExerciseMapper.toWorkoutExerciseDto(workoutExercise);
    }

    public WorkoutExerciseDto updateWorkoutExercise(long id, WorkoutExerciseDto workoutExerciseDto) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutExercise not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(workoutExerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        workoutExercise.setWorkout(workoutRepository.findById(workoutExerciseDto.getWorkoutId())
                .orElseThrow(() -> new AppException("Workout not found", HttpStatus.NOT_FOUND)));

        workoutExercise.setExercise(exerciseRepository.findById(workoutExerciseDto.getExerciseId())
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND)));

        workoutExercise.setSequence(workoutExerciseDto.getSequence());
        workoutExercise.setCreatedBy(user);

        WorkoutExercise updatedWorkoutExercise = workoutExerciseRepository.save(workoutExercise);
        return workoutExerciseMapper.toWorkoutExerciseDto(updatedWorkoutExercise);
    }

    public void deleteWorkoutExercise(long id) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutExercise not found", HttpStatus.NOT_FOUND));
        workoutExerciseRepository.delete(workoutExercise);
    }
}
