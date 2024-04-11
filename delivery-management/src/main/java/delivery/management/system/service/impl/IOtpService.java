package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import common.notification.service.EmailService;
import delivery.management.system.helper.EmailServiceHelper;
import delivery.management.system.mapper.OtpMapper;
import delivery.management.system.model.entity.Otp;
import delivery.management.system.model.entity.User;
import delivery.management.system.repository.OtpRepository;
import delivery.management.system.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IOtpService implements OtpService {

    @Value("${send.email.otp.second-time}")
    private long time;
    private final OtpRepository otpRepository;
    private final OtpMapper otpMapper;
    private final EmailService emailService;
    private final EmailServiceHelper emailServiceHelper;
    private final ExceptionService exceptionService;


    @Override
    @Transactional
    public void createOtp(User user) {

        Optional<Otp> otp = otpRepository.findOtpByUser(user);

        Otp generateOtp = generateOtp();

        if (otp.isEmpty()) {
            generateOtp.setUser(user);
            otpRepository.save(generateOtp);
            emailService.sendEmail(emailServiceHelper.generateEmailRequest(generateOtp, user));
            return;
        }

        generateOtp.setUser(user);
        otpMapper.map(otp.get(), generateOtp);

        emailService.sendEmail(emailServiceHelper.generateEmailRequest(generateOtp, user));
    }

    private Otp generateOtp() {
        Random random = new Random();
        int randomOtp = random.nextInt(1000, 9999);

        return Otp.builder()
                .confirm(true)
                .otp(randomOtp)
                .expired(LocalDateTime.now().plusSeconds(time))
                .build();
    }

    public Otp findByCheckOtp(String username, int otp) {

        return otpRepository
                .findByCheckOtp(username, otp)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("Otp expired", HttpStatus.NOT_FOUND)));
    }

}
