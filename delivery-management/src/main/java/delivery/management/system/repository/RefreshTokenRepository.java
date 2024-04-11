package delivery.management.system.repository;

import delivery.management.system.model.entity.RefreshToken;
import delivery.management.system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByNameAndRevoked(String refreshToken, boolean revoked);
}
