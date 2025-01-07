package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {
    private Long id;
    private String name;
    private String image;
    private String bodyPart;
    private String category;
    private String instructions;
    private Long userId; // Represents the ID of the user who created the exercise
}
