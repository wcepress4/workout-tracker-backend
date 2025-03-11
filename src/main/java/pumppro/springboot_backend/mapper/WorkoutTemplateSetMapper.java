package pumppro.springboot_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pumppro.springboot_backend.dtos.WorkoutTemplateSetDto;
import pumppro.springboot_backend.model.WorkoutTemplateSet;

@Mapper(componentModel = "spring")
public interface WorkoutTemplateSetMapper {
    @Mappings({
            @Mapping(source = "workoutTemplateExercise.id", target = "workoutTemplateExerciseId"),
            @Mapping(source = "set.id", target = "setId")
    })
    WorkoutTemplateSetDto toWorkoutTemplateSetDto(WorkoutTemplateSet workoutTemplateSet);

    @Mappings({
            @Mapping(source = "workoutTemplateExerciseId", target = "workoutTemplateExercise.id"),
            @Mapping(source = "setId", target = "set.id")
    })
    WorkoutTemplateSet toWorkoutTemplateSet(WorkoutTemplateSetDto workoutTemplateSetDto);
}
