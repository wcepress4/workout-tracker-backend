package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutSetDto {
    private Long id;
    private Long workoutExerciseId;
    private Long setId;
    private Long userId; // Represents the ID of the user who created the workout set
}
