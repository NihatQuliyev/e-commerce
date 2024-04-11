package delivery.management.system.service;

import delivery.management.system.model.entity.Otp;
import delivery.management.system.model.entity.User;

public interface OtpService {

    void createOtp(User user);
    Otp findByCheckOtp(String username, int otp);
}
