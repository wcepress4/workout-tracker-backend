package pumppro.springboot_backend.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "workout_template")
public class WorkoutTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    @Column(columnDefinition = "TEXT") // Ensures large text can be stored
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = true) // Nullable for default templates
    private User createdBy;
}
