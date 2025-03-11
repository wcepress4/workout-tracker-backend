package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutTemplateExerciseDto {
    private Long id;
    private Long workoutTemplateId;
    private Long exerciseId;
    private int sequence;
    private Long userId;
}
