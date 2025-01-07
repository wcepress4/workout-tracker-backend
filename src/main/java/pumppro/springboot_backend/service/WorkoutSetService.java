package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutSetDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.WorkoutSetMapper;
import pumppro.springboot_backend.model.WorkoutSet;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.WorkoutSetRepository;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.repository.WorkoutExerciseRepository;
import pumppro.springboot_backend.repository.SetRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutSetService {

    private final WorkoutSetRepository workoutSetRepository;
    private final UserRepository userRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final SetRepository setRepository;
    private final WorkoutSetMapper workoutSetMapper;

    public WorkoutSetDto createWorkoutSet(WorkoutSetDto workoutSetDto) {
        User user = userRepository.findById(workoutSetDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        WorkoutSet workoutSet = workoutSetMapper.toWorkoutSet(workoutSetDto);
        workoutSet.setCreatedBy(user);

        WorkoutSet savedWorkoutSet = workoutSetRepository.save(workoutSet);
        return workoutSetMapper.toWorkoutSetDto(savedWorkoutSet);
    }

    public List<WorkoutSetDto> getAllWorkoutSets() {
        return workoutSetRepository.findAll().stream()
                .map(workoutSetMapper::toWorkoutSetDto)
                .collect(Collectors.toList());
    }

    public WorkoutSetDto getWorkoutSetById(long id) {
        WorkoutSet workoutSet = workoutSetRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutSet not found", HttpStatus.NOT_FOUND));
        return workoutSetMapper.toWorkoutSetDto(workoutSet);
    }

    public WorkoutSetDto updateWorkoutSet(long id, WorkoutSetDto workoutSetDto) {
        WorkoutSet workoutSet = workoutSetRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutSet not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(workoutSetDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        workoutSet.setWorkoutExercise(workoutExerciseRepository.findById(workoutSetDto.getWorkoutExerciseId())
                .orElseThrow(() -> new AppException("WorkoutExercise not found", HttpStatus.NOT_FOUND)));

        workoutSet.setSet(setRepository.findById(workoutSetDto.getSetId())
                .orElseThrow(() -> new AppException("Set not found", HttpStatus.NOT_FOUND)));

        workoutSet.setCreatedBy(user);

        WorkoutSet updatedWorkoutSet = workoutSetRepository.save(workoutSet);
        return workoutSetMapper.toWorkoutSetDto(updatedWorkoutSet);
    }

    public void deleteWorkoutSet(long id) {
        WorkoutSet workoutSet = workoutSetRepository.findById(id)
                .orElseThrow(() -> new AppException("WorkoutSet not found", HttpStatus.NOT_FOUND));
        workoutSetRepository.delete(workoutSet);
    }
}
