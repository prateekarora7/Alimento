package com.alimento.prototype.services.user;

import com.alimento.prototype.entities.user.User;
import org.springframework.data.repository.query.Param;

public interface UserService {

    void saveUser(User user);

    User getUserByUsername(String userId);

    User getUserByEmail(String email);

    void updateUsername(String oldUsername, String newUsername);

    void updatePhoneNumber(String username, String newPhoneNumber);

    void updateName(String username, String firstName, String lastName);

    void updatePassword(String username, String newPassword);

    void deleteUserByUsername(String username);

    void deleteUserByEmail( String email);
}
