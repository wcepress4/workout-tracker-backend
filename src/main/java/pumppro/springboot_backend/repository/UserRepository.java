package pumppro.springboot_backend.repository;

import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // all crud database methods

} //UserRepository
