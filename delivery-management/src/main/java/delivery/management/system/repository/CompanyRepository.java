package delivery.management.system.repository;

import delivery.management.system.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
  Optional<Company> findByName(String name);
}