package delivery.management.system.service;

import delivery.management.system.model.dto.request.PasswordRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.entity.User;
import org.springframework.http.ResponseEntity;


public interface UserService{

    ResponseEntity<Void> registration(UserRequestDto user);

    ResponseEntity<Void> confirmation(String token);

    ResponseEntity<String> userRenewPassword(String username);

    ResponseEntity<String> resetPassword(String username, int otp, PasswordRequestDto updatePassword);

    ResponseEntity<User> findByUsername(String username);

    int customerCount();
    int driverCount();
}
