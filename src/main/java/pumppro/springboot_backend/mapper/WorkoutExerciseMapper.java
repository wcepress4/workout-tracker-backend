package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.WorkoutExercise;
import pumppro.springboot_backend.dtos.WorkoutExerciseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutExerciseMapper {

    @Mapping(source = "workout.id", target = "workoutId")
    @Mapping(source = "exercise.id", target = "exerciseId")
    @Mapping(source = "createdBy.id", target = "userId")
    WorkoutExerciseDto toWorkoutExerciseDto(WorkoutExercise workoutExercise);

    @Mapping(source = "workoutId", target = "workout.id")
    @Mapping(source = "exerciseId", target = "exercise.id")
    @Mapping(source = "userId", target = "createdBy.id")
    WorkoutExercise toWorkoutExercise(WorkoutExerciseDto workoutExerciseDto);
}
