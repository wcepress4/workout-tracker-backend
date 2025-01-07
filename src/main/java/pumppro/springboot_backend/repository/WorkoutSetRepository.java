package pumppro.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.model.WorkoutSet;

@Repository
public interface WorkoutSetRepository extends JpaRepository<WorkoutSet, Long> {

}

