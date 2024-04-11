package delivery.management.system.repository;

import delivery.management.system.model.entity.Driver;
import delivery.management.system.model.entity.DriverType;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findDriversByDriverType_Status(DriverStatus status);
    List<Driver> findAllByBusy(boolean busy);

    Optional<Driver> findByUser(User user);
}