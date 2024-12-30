package com.alimento.prototype.services.user;

import com.alimento.prototype.entities.user.User;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    User getUserByUsername(String userId);

    User getUserByEmail(String email);

    List<User> getUsersByCreationDate(@Param("date") LocalDate date);

    void updateUsername(String oldUsername, String newUsername);

    void updatePhoneNumber(String username, String newPhoneNumber);

    void updateName(String username, String firstName, String lastName);

    void updatePassword(String username, String oldPassword, String newPassword);

    void deleteUserByUsername(String username);

    void deleteUserByEmail( String email);
}
