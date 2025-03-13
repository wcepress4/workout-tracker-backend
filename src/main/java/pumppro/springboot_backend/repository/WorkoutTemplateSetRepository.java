package pumppro.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.model.WorkoutTemplateSet;

import java.util.List;

@Repository
public interface WorkoutTemplateSetRepository extends JpaRepository<WorkoutTemplateSet, Long> {
    List<WorkoutTemplateSet> findByWorkoutTemplateExerciseId(Long workoutTemplateExerciseId);
}
