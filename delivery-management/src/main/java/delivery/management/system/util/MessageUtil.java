package delivery.management.system.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageUtil {


    private final MessageSource messageSource;


    public HttpServletRequest getRequest() {

        return  ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes()))
                .getRequest();
    }


    public String getMessages(String messages, Object... dynamicKey ) {

        return messageSource.getMessage(messages, dynamicKey, getRequest().getLocale() );
    }
}
