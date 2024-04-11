package delivery.management.system.model.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Exceptions {
    TOKEN_EXPIRED_EXCEPTION("exception.unauthorized.token-expired", HttpStatus.UNAUTHORIZED),
    ID_NOT_FOUND_EXCEPTION("exception.id-not-found", HttpStatus.BAD_REQUEST),
    USER_MUST_NOT_BE_NULL("exception.null-pointer.user", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_MUST_NOT_BE_NULL("exception.null-pointer.refresh-token", HttpStatus.BAD_REQUEST),
    USER_NULL_POINTER_EXCEPTION("exception.null-pointer", HttpStatus.BAD_REQUEST);
    private final String message;
    private final HttpStatus httpStatus;
    Exceptions(String message,HttpStatus  httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
