package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutExerciseDto {
    private Long id;
    private Long workoutId;
    private Long exerciseId;
    private int sequence;
    private Long userId; // Represents the ID of the user who created the workout exercise
}

