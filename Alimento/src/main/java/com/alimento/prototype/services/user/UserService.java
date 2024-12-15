package com.alimento.prototype.services.user;

import com.alimento.prototype.entities.user.User;

public interface UserService {

    void saveUser(User user);

    User getUserByEmail(String email);

    User getUserByUsername(String userId);
}
