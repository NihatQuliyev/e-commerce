package delivery.management.system.service.impl;

import common.exception.model.dto.response.ExceptionResponse;
import common.exception.model.exception.ApplicationException;
import common.notification.dto.request.EmailRequest;
import common.notification.service.EmailService;
import delivery.management.system.helper.EmailServiceHelper;
import delivery.management.system.helper.TokenServiceHelper;
import delivery.management.system.model.entity.Token;
import delivery.management.system.model.entity.User;
import delivery.management.system.repository.TokenRepository;
import delivery.management.system.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ITokenService implements TokenService {

    private final TokenServiceHelper tokenServiceHelper;
    private final TokenRepository tokenRepository;
    private final EmailServiceHelper emailServiceHelper;
    private final EmailService emailService;

    @Override
    public void confirm(User user) {
        Token token = tokenServiceHelper.getTokenBuild(user);
        tokenRepository.save(token);
        EmailRequest emailRequest = emailServiceHelper.sendEmailToVerify(token, user);

        emailService.sendEmail(emailRequest);
    }

    @Override
    public Map<String, User> getByToken(String token) {
        Token userToken = findByToken(token);

        return Map.of(userToken.getToken(),userToken.getUser());
    }

    @Override
    public void delete(String token) {
        tokenRepository.delete(findByToken(token));
    }

    private Token findByToken(String token) {

        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new ApplicationException(ExceptionResponse.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("Token not found") // FIXME i18
                        .build()));
    }


}
