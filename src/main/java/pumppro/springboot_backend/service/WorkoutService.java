package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.WorkoutDto;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.exception.ResourceNotFoundException;
import pumppro.springboot_backend.mapper.WorkoutMapper;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.model.Workout;
import pumppro.springboot_backend.repository.UserRepository;
import pumppro.springboot_backend.repository.WorkoutRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final UserRepository userRepository;

    public WorkoutDto createWorkout(WorkoutDto workoutDto) {
        User user = userRepository.findById(workoutDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Workout workout = workoutMapper.toWorkout(workoutDto);
        workout.setCreatedBy(user);

        Workout savedWorkout = workoutRepository.save(workout);
        return workoutMapper.toWorkoutDto(savedWorkout);
    }

    public List<WorkoutDto> getAllWorkouts() {
        return workoutRepository.findAll().stream()
                .map(workoutMapper::toWorkoutDto)
                .collect(Collectors.toList());
    }

    public WorkoutDto getWorkoutById(long id) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout not found", HttpStatus.NOT_FOUND));
        return workoutMapper.toWorkoutDto(workout);
    }

    public WorkoutDto updateWorkout(long id, WorkoutDto workoutDto) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(workoutDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        workout.setName(workoutDto.getName());
//        workout.setDate(workoutDto.getDate());
        workout.setStartTime(workoutDto.getStartTime());
        workout.setEndTime(workoutDto.getEndTime());
        workout.setCreatedBy(user);

        Workout updatedWorkout = workoutRepository.save(workout);
        return workoutMapper.toWorkoutDto(updatedWorkout);
    }

    public void deleteWorkout(long id) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new AppException("Workout not found", HttpStatus.NOT_FOUND));
        workoutRepository.delete(workout);
    }
}
