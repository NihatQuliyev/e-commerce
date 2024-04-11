package delivery.management.system.helper;

import common.notification.dto.request.EmailRequest;
import delivery.management.system.model.entity.Otp;
import delivery.management.system.model.entity.Token;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.SubjectType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    @Value("${application.mail.http}")
    String http;

    @Value("${application.mail.host}")
    String host;

    @Value("${application.mail.confirmation}")
    String confirmation;

    @Value("${application.mail.reset-password}")
    String resetPassword;


    public EmailRequest sendEmailToVerify(Token token, User user) {
        String url =http + host + getServerPort() +confirmation + token.getToken();

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectType.REGISTRATION.getName())
                .text("<p> Hi, " + user.getName() + ", <p>" +
                        "<p>Thank you for registering with us," +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public EmailRequest generateEmailRequest(Otp otp, User user) {
        String url =http + host + getServerPort() +resetPassword + user.getEmail() + "&otp=" + otp.getOtp();

        String text = "<p> Hi, " + user.getEmail() + ", <p>" +
                "<p>Thank you for reset password with us," +
                "Please, follow the link below to complete your reset password.<p>" +
                "<a href=\"" + url + "\">Verify your email to reset password<a>" +
                "<p> Thank you <br> Users Reset password Portal Service";

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectType.FORGET_PASSWORD.getName())
                .text(text)
                .build();
    }


    private String getServerPort(){
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder
                        .getRequestAttributes()))
                .getRequest();

        return  ":" + httpServletRequest.getServerPort();
    }
}
