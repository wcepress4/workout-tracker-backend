package pumppro.springboot_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pumppro.springboot_backend.dtos.WorkoutTemplateExerciseDto;
import pumppro.springboot_backend.model.WorkoutTemplateExercise;

@Mapper(componentModel = "spring")
public interface WorkoutTemplateExerciseMapper {
    @Mappings({
            @Mapping(source = "workoutTemplate.id", target = "workoutTemplateId"),
            @Mapping(source = "exercise.id", target = "exerciseId")
    })
    WorkoutTemplateExerciseDto toWorkoutTemplateExerciseDto(WorkoutTemplateExercise workoutTemplateExercise);

    @Mappings({
            @Mapping(source = "workoutTemplateId", target = "workoutTemplate.id"),
            @Mapping(source = "exerciseId", target = "exercise.id")
    })
    WorkoutTemplateExercise toWorkoutTemplateExercise(WorkoutTemplateExerciseDto workoutTemplateExerciseDto);
}

