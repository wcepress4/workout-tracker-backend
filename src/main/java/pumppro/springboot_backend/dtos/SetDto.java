package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetDto {
    private Long id;
    private Integer reps;
    private Double weight;
    private Long userId; // Represents the ID of the user who created the exercise
}
