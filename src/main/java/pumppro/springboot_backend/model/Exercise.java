package pumppro.springboot_backend.model;

import lombok.*;
import pumppro.springboot_backend.enums.Role;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "exercise")
public class Exercise {

    // I want to see if this comment will be here...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name;

    @Column
    private String image; // URL or path to the image

    @Column(nullable = false)
    @Size(max = 100)
    private String bodyPart;

    @Column(nullable = false)
    @Size(max = 100)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
