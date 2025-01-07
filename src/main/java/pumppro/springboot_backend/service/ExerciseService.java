package pumppro.springboot_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pumppro.springboot_backend.dtos.ExerciseDto;
import pumppro.springboot_backend.enums.Role;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.ExerciseMapper;
import pumppro.springboot_backend.model.Exercise;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.ExerciseRepository;
import pumppro.springboot_backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseMapper exerciseMapper;

    public List<ExerciseDto> getAllExercisesPersonal(Long userId, String bodyPart, String category) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        List<Exercise> exercises = exerciseRepository.findByFilters(userId, null, bodyPart, category);
        return exercises.stream()
                .map(exerciseMapper::toExerciseDto)
                .collect(Collectors.toList());
    }

    public List<ExerciseDto> getAllExercisesDefault(String bodyPart, String category) {
        List<Exercise> exercises = exerciseRepository.findByFilters(null, Role.ADMIN, bodyPart, category);
        return exercises.stream()
                .map(exerciseMapper::toExerciseDto)
                .collect(Collectors.toList());
    }

    public List<ExerciseDto> getAllExercises(Long userId, String bodyPart, String category) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        List<Exercise> exercises = exerciseRepository.findByFiltersAdmin(userId, Role.ADMIN, bodyPart, category);
        return exercises.stream()
                .map(exerciseMapper::toExerciseDto)
                .collect(Collectors.toList());
    }

    public List<ExerciseDto> getAllExercisesAdmin(String bodyPart, String category) {
        List<Exercise> exercises = exerciseRepository.findByFilters(null, null, bodyPart, category);
        return exercises.stream()
                .map(exerciseMapper::toExerciseDto)
                .collect(Collectors.toList());
    }

    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        User user = userRepository.findById(exerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Exercise exercise = exerciseMapper.toExercise(exerciseDto);
        exercise.setCreatedBy(user);

        Exercise savedExercise = exerciseRepository.save(exercise);
        return exerciseMapper.toExerciseDto(savedExercise);
    }

    public ExerciseDto getExerciseById(long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND));
        return exerciseMapper.toExerciseDto(exercise);
    }

    public ExerciseDto getExerciseByName(String name) {
        Exercise exercise = exerciseRepository.findByName(name)
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND));
        return exerciseMapper.toExerciseDto(exercise);
    }

    public boolean getAdminStatusByExerciseCreator(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND));

        User createdBy = exercise.getCreatedBy();
        return createdBy.getRole() == Role.ADMIN;
    }

    public ExerciseDto updateExercise(long id, ExerciseDto exerciseDto) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(exerciseDto.getUserId())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        exercise.setName(exerciseDto.getName());
        exercise.setBodyPart(exerciseDto.getBodyPart());
        exercise.setCategory(exerciseDto.getCategory());
//        exercise.setImage(exerciseDto.getImage());
        exercise.setInstructions(exerciseDto.getInstructions());
        exercise.setCreatedBy(user);

        Exercise updatedExercise = exerciseRepository.save(exercise);
        return exerciseMapper.toExerciseDto(updatedExercise);
    }

    public void deleteExercise(long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new AppException("Exercise not found", HttpStatus.NOT_FOUND));
        exerciseRepository.delete(exercise);
    }
}
