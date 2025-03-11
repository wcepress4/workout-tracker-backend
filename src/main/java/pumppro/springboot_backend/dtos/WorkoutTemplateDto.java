package pumppro.springboot_backend.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutTemplateDto {
    private Long id;
    private String name;
    private String description;
    private Long userId; // Null for default templates, set for user-created ones
}

