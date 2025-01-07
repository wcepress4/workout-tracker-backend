package pumppro.springboot_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pumppro.springboot_backend.enums.Role;
import pumppro.springboot_backend.model.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
//    List<Exercise> findByCategoryAndBodyPart(String category, String bodyPart);
//
//    List<Exercise> findByCreatedByIdAndCategoryAndBodyPart(Long userId, String category, String bodyPart);
//
//    List<Exercise> findByCreatedByRoleAndCategoryAndBodyPart(Role role, String category, String bodyPart);
//
//    List<Exercise> findByCreatedByIdAndCreatedByRoleAndCategoryAndBodyPart(Long userId, Role role, String category, String bodyPart);

    @Query("SELECT e FROM Exercise e " +
            "WHERE (:userId IS NULL OR e.createdBy.id = :userId) " +
            "AND (:role IS NULL OR e.createdBy.role = :role) " +
            "AND (:bodyPart IS NULL OR e.bodyPart = :bodyPart) " +
            "AND (:category IS NULL OR e.category = :category)")
    List<Exercise> findByFilters(
            @Param("userId") Long userId,
            @Param("role") Role role,
            @Param("bodyPart") String bodyPart,
            @Param("category") String category);

    @Query("SELECT e FROM Exercise e " +
            "WHERE (:userId IS NULL OR e.createdBy.id = :userId OR e.createdBy.role = :role) " +
            "AND (:bodyPart IS NULL OR e.bodyPart = :bodyPart) " +
            "AND (:category IS NULL OR e.category = :category)")
    List<Exercise> findByFiltersAdmin(
            @Param("userId") Long userId,
            @Param("role") Role role,
            @Param("bodyPart") String bodyPart,
            @Param("category") String category);

    Optional<Exercise> findByName(String name);

//    List<Exercise> findById_Role_Category_BodyPart(Long userId, Role role, String category, String bodyPart);
}
