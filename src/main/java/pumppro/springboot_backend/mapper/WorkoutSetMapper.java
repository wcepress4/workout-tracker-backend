package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.WorkoutSet;
import pumppro.springboot_backend.dtos.WorkoutSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutSetMapper {

    @Mapping(source = "workoutExercise.id", target = "workoutExerciseId")
    @Mapping(source = "set.id", target = "setId")
    @Mapping(source = "createdBy.id", target = "userId")
    WorkoutSetDto toWorkoutSetDto(WorkoutSet workoutSet);

    @Mapping(source = "workoutExerciseId", target = "workoutExercise.id")
    @Mapping(source = "setId", target = "set.id")
    @Mapping(source = "userId", target = "createdBy.id")
    WorkoutSet toWorkoutSet(WorkoutSetDto workoutSetDto);
}
