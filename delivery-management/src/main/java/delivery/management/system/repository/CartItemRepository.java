package delivery.management.system.repository;

import delivery.management.system.model.entity.CartItem;
import delivery.management.system.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  Optional<CartItem> findByProduct(Product product);
}