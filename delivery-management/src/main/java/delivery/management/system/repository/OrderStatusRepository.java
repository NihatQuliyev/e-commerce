package delivery.management.system.repository;

import delivery.management.system.model.entity.OrderStatus;
import delivery.management.system.model.enums.OrderStatusEnums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByName(OrderStatusEnums orderStatusEnums);
}