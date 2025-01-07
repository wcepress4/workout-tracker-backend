package pumppro.springboot_backend.dtos;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {
    private Long id;
    private String name;
//    private Instant dateCompleted;
    private Instant startTime;
    private Instant endTime;
    private Long userId; // Represents the ID of the user who created the exercise
}
