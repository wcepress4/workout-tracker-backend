package pumppro.springboot_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pumppro.springboot_backend.dtos.WorkoutTemplateDto;
import pumppro.springboot_backend.model.WorkoutTemplate;

@Mapper(componentModel = "spring")
public interface WorkoutTemplateMapper {

    @Mapping(source = "createdBy.id", target = "userId")
    WorkoutTemplateDto toWorkoutTemplateDto(WorkoutTemplate workoutTemplate);

    @Mapping(source = "userId", target = "createdBy.id")
    WorkoutTemplate toWorkoutTemplate(WorkoutTemplateDto workoutTemplateDto);
}
