package delivery.management.system.repository;

import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser (User user);

}
