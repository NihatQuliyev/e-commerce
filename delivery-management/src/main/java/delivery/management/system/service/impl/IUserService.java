package delivery.management.system.service.impl;


import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.UserMapper;
import delivery.management.system.model.dto.request.PasswordRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.entity.Cart;
import delivery.management.system.model.entity.Otp;
import delivery.management.system.model.entity.Role;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.RoleType;
import delivery.management.system.repository.UserRepository;
import delivery.management.system.service.*;
import delivery.management.system.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class IUserService implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final OtpService otpService;
    private final ExceptionService exceptionService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final MessageUtil messagesUtil;
    private final CartService cartService;


    @Override
    public ResponseEntity<Void> registration(UserRequestDto userRequest) {
        User user = userMapper.map(userRequest);
        Role role = roleService.findByRole(RoleType.CUSTOMER.name());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(role);
        repository.save(user);
        tokenService.confirm(user);
        cartService.saveCart(user);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> confirmation(String token) {
        Map<String, User> byToken = tokenService.getByToken(token);
        long id = byToken.get(token).getId();
        User user = findById(id);
        user.setEnable(true);
        tokenService.delete(token);

        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<String> userRenewPassword(String username) {
        User user = findByUsername(username).getBody();
        log.error("{}",user);
        otpService.createOtp(user);

        return ResponseEntity.ok(messagesUtil.getMessages( "SEND_EMAIL_SUCCESSFULLY"));
    }

    @Override
    @Transactional
    public ResponseEntity<String> resetPassword(String username,
                                                int otp,
                                                PasswordRequestDto updatePassword) {
        Otp checkOtp = otpService.findByCheckOtp(username, otp);
        checkOtp.getUser().setPassword(passwordEncoder.encode(updatePassword.getUpdatePassword()));

        return ResponseEntity.ok(messagesUtil.getMessages("RESET_PASSWORD_SUCCESSFULLY"));
    }

    private User findById(long id) {

        return repository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(messagesUtil.getMessages("USERNAME_NOT_FOUND",id), HttpStatus.NOT_FOUND)));
    }

    public ResponseEntity<User> findByUsername(String username) {

        return ResponseEntity.ok().body(repository
                .findByEmail(username)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse(messagesUtil.getMessages("USERNAME_NOT_FOUND"), HttpStatus.NOT_FOUND))));

    }

    @Override
    public int customerCount() {
        return repository.findUsersByRole_Name(RoleType.CUSTOMER.name()).size();
    }

    @Override
    public int driverCount() {
        return repository.findUsersByRole_Name(RoleType.DRIVER.name()).size();
    }
}
