package delivery.management.system.repository;

import delivery.management.system.model.entity.Otp;
import delivery.management.system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    @Query("select o from Otp o inner join _user u on o.user = u " +
            "where u.email =:username " +
            "and o.otp =:otp " +
            "and o.expired > current_timestamp")

    Optional<Otp> findByCheckOtp(
            @Param("username") String username,
            @Param("otp") Integer otp);
    Optional<Otp> findOtpByUser(User user);
}