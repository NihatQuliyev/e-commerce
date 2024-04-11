package delivery.management.system.controller;

import delivery.management.system.model.dto.request.AuthRequestDto;
import delivery.management.system.model.dto.response.AuthenticationResponseDto;
import delivery.management.system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/authentication")
    public AuthenticationResponseDto authentication(@RequestBody @Valid AuthRequestDto user) {

        return authService.authentication(user);
    }
}
