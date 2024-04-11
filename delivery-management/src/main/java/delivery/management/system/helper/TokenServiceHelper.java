package delivery.management.system.helper;

import delivery.management.system.model.entity.Token;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class TokenServiceHelper {

    public Token getTokenBuild(User user) {

        String uuid = String.valueOf(UUID.randomUUID());

        return Token.builder()
                .token(uuid)
                .tokenType(TokenType.CONFIRMATION)
                .creatAt(now())
                .user(user)
                .confirm(true)
                .build();
    }
}
