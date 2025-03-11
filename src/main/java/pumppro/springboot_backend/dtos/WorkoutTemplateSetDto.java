package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutTemplateSetDto {
    private Long id;
    private Long workoutTemplateExerciseId;
    private Long setId;
    private Long userId;
}
