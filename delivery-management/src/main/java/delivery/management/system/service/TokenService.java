package delivery.management.system.service;

import delivery.management.system.model.entity.User;

import java.util.Map;

public interface TokenService {

    void confirm(User user);

    Map<String, User> getByToken(String token);

    void delete(String token);


}
