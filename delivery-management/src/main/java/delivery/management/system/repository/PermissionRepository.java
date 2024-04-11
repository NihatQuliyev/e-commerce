package delivery.management.system.repository;

import delivery.management.system.model.entity.Permission;
import delivery.management.system.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {


}