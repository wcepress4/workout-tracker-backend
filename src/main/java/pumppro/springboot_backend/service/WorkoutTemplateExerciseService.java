package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutTemplateExerciseDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.WorkoutTemplateExerciseMapper;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.model.WorkoutTemplateExercise;
import pumppro.springboot_backend.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutTemplateExerciseService {

    private final WorkoutTemplateExerciseRepository workoutTemplateExerciseRepository;
    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final WorkoutTemplateExerciseMapper workoutTemplateExerciseMapper;

    public WorkoutTemplateExerciseDto createWorkoutTemplateExercise(WorkoutTemplateExerciseDto workoutTemplateExerciseDto) {
        User user = userRepository.findById(workoutTemplateExerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        WorkoutTemplateExercise workoutTemplateExercise = workoutTemplateExerciseMapper.toWorkoutTemplateExercise(workoutTemplateExerciseDto);
        workoutTemplateExercise.setCreatedBy(user);


        WorkoutTemplateExercise savedWorkoutTemplateExercise = workoutTemplateExerciseRepository.save(workoutTemplateExercise);
        return workoutTemplateExerciseMapper.toWorkoutTemplateExerciseDto(savedWorkoutTemplateExercise);
    }

    public List<WorkoutTemplateExerciseDto> getAllWorkoutTemplateExercises() {
        return workoutTemplateExerciseRepository.findAll().stream()
                .map(workoutTemplateExerciseMapper::toWorkoutTemplateExerciseDto)
                .collect(Collectors.toList());
    }

    public WorkoutTemplateExerciseDto getWorkoutTemplateExerciseById(long id) {
        WorkoutTemplateExercise workoutTemplateExercise = workoutTemplateExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout template exercise not found", HttpStatus.NOT_FOUND));
        return workoutTemplateExerciseMapper.toWorkoutTemplateExerciseDto(workoutTemplateExercise);
    }

    public WorkoutTemplateExerciseDto updateWorkoutTemplateExercise(long id, WorkoutTemplateExerciseDto workoutTemplateExerciseDto) {
        WorkoutTemplateExercise workoutTemplateExercise = workoutTemplateExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout template exercise not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(workoutTemplateExerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        workoutTemplateExercise.setExercise(exerciseRepository.findById(workoutTemplateExerciseDto.getExerciseId())
                        .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND)));

        workoutTemplateExercise.setWorkoutTemplate(workoutTemplateRepository.findById(workoutTemplateExerciseDto.getWorkoutTemplateId())
                        .orElseThrow(() -> new AppException("Workout template not found", HttpStatus.NOT_FOUND)));

        workoutTemplateExercise.setCreatedBy(user);

        WorkoutTemplateExercise updatedWorkoutTemplateExercise = workoutTemplateExerciseRepository.save(workoutTemplateExercise);
        return workoutTemplateExerciseMapper.toWorkoutTemplateExerciseDto(updatedWorkoutTemplateExercise);
    }

    public void deleteWorkoutTemplateExerciseById(long id) {
        WorkoutTemplateExercise workoutTemplateExercise = workoutTemplateExerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout template exercise not found", HttpStatus.NOT_FOUND));
        workoutTemplateExerciseRepository.delete(workoutTemplateExercise);
    }
}
