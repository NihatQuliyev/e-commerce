package delivery.management.system.repository;

import delivery.management.system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from _user u where u.email =:email and u.isEnable = true")
    Optional<User> findByEmailAndIsEnableTrue(@Param("email") String email);



    Optional<User> findByEmail(String username);

    //FIXME
    List<User> findUsersByRole_Name(String role);

}
