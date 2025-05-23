package pumppro.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.model.WorkoutTemplateExercise;

import java.util.List;

@Repository
public interface WorkoutTemplateExerciseRepository extends JpaRepository<WorkoutTemplateExercise, Long> {
    List<WorkoutTemplateExercise> findByWorkoutTemplateIdOrderBySequenceAsc(Long workoutTemplateId);
}

