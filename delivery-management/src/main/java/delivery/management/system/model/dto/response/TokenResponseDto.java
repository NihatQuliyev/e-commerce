package delivery.management.system.model.dto.response;

import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.TokenType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TokenResponseDto {
    private String token;
    private LocalDateTime creatAt;
    private boolean confirm;
    @ManyToOne
    private User user;
    @Enumerated
    private TokenType tokenType;
}
