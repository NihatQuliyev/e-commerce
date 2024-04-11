package delivery.management.system.repository;

import delivery.management.system.model.entity.DriverType;
import delivery.management.system.model.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverTypeRepository extends JpaRepository<DriverType, Long> {

    Optional<DriverType> findByStatus(DriverStatus status);
}