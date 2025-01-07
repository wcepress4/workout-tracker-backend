package pumppro.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.model.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

}

