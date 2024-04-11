package delivery.management.system.controller;

import delivery.management.system.model.dto.request.PasswordRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("customer")
    public ResponseEntity<Void> registration(@RequestBody @Valid UserRequestDto user) {

        return userService.registration(user);
    }

    @GetMapping("confirmation")
    public ResponseEntity<Void> confirmation(@RequestParam String token) {

        return userService.confirmation(token);
    }


    @PostMapping("renew-password/{username}")
    public ResponseEntity<String> userRenewPassword(@PathVariable String username) {
        System.out.println(username);

        return userService.userRenewPassword(username);
    }

    @PutMapping("reset-password")
    public ResponseEntity<String> userResetPassword(@RequestParam String username,
                                                    @RequestParam int otp,
                                                    @RequestBody @Valid PasswordRequestDto updatePassword) {

        return userService.resetPassword(username, otp, updatePassword);
    }
}
