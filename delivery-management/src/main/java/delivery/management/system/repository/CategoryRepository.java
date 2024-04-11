package delivery.management.system.repository;

import delivery.management.system.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findAllByStatusTrue();

  Optional<Category> findByIdAndStatusTrue(long id);
}