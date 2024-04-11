package delivery.management.system.service;

import delivery.management.system.model.dto.request.AuthRequestDto;
import delivery.management.system.model.dto.response.AuthenticationResponseDto;
import delivery.management.system.model.entity.User;

public interface AuthService {


    AuthenticationResponseDto authentication(AuthRequestDto dto);
    AuthenticationResponseDto accessTokensByRefreshToken(String refreshToken);
    User getAuthenticatedUser();
}
