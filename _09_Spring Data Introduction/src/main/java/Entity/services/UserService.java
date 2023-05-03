package Entity.services;

import Entity.models.User;

import java.math.BigDecimal;

public interface UserService {
    void register(String username, int age);

    User findByUsername(String username);

}
