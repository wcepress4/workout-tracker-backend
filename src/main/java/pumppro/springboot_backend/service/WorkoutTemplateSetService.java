package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutTemplateSetDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.WorkoutTemplateSetMapper;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.model.WorkoutTemplateSet;
import pumppro.springboot_backend.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutTemplateSetService {
    private final WorkoutTemplateExerciseRepository workoutTemplateExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetRepository setRepository;
    private final WorkoutTemplateRepository workoutTemplateRepository;
    private final WorkoutTemplateSetMapper workoutTemplateSetMapper;
    private final WorkoutTemplateSetRepository workoutTemplateSetRepository;

    public WorkoutTemplateSetDto createWorkoutTemplateSet(WorkoutTemplateSetDto workoutTemplateSetDto) {
        User user = userRepository.findById(workoutTemplateSetDto.getUserId())
                .orElseThrow(() -> new AppException("User Not Found", HttpStatus.NOT_FOUND));

        WorkoutTemplateSet workoutTemplateSet = workoutTemplateSetMapper.toWorkoutTemplateSet(workoutTemplateSetDto);
        workoutTemplateSet.setCreatedBy(user);

        WorkoutTemplateSet savedWorkoutTemplateSet = workoutTemplateSetRepository.save(workoutTemplateSet);
        return workoutTemplateSetMapper.toWorkoutTemplateSetDto(savedWorkoutTemplateSet);
    }

    public List<WorkoutTemplateSetDto> getAllWorkoutTemplateSets() {
        return workoutTemplateSetRepository.findAll().stream()
                .map(workoutTemplateSetMapper::toWorkoutTemplateSetDto)
                .collect(Collectors.toList());
    }

    public WorkoutTemplateSetDto getWorkoutTemplateSetById(long id) {
        WorkoutTemplateSet workoutTemplateSet = workoutTemplateSetRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout template set not found", HttpStatus.NOT_FOUND));
        return workoutTemplateSetMapper.toWorkoutTemplateSetDto(workoutTemplateSet);
    }

    public WorkoutTemplateSetDto updateWorkoutTemplateSet(long id, WorkoutTemplateSetDto workoutTemplateSetDto) {
        WorkoutTemplateSet workoutTemplateSet = workoutTemplateSetRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout template set not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(workoutTemplateSetDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        workoutTemplateSet.setWorkoutTemplateExercise(workoutTemplateExerciseRepository.findById(workoutTemplateSetDto.getWorkoutTemplateExerciseId())
                .orElseThrow(() -> new AppException("Workout template set not found", HttpStatus.NOT_FOUND)));

        workoutTemplateSet.setSet(workout);
    }
}
