package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.Workout;
import pumppro.springboot_backend.dtos.WorkoutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(source = "createdBy.id", target = "userId")
    WorkoutDto toWorkoutDto(Workout workout);

    @Mapping(source = "userId", target = "createdBy.id")
    Workout toWorkout(WorkoutDto workoutDto);
}