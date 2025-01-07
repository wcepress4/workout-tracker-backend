package pumppro.springboot_backend.mapper;

import pumppro.springboot_backend.model.Exercise;
import pumppro.springboot_backend.dtos.ExerciseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    @Mapping(source = "createdBy.id", target = "userId")
    ExerciseDto toExerciseDto(Exercise exercise);

    @Mapping(source = "userId", target = "createdBy.id")
    Exercise toExercise(ExerciseDto exerciseDto);
}
