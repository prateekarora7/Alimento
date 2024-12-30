package com.alimento.prototype.repositories.user;

import com.alimento.prototype.entities.user.User;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Method register or save new user
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (email, password, username, first_name, last_name, phone_number, created_at) " +
            "VALUES (:email, :password, :username, :firstName, :lastName, :phoneNo, :createdAt)", nativeQuery = true)
    void saveUser(@Param("email") String email, @Param("password") String password, @Param("username") String username,
                  @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNo") String phoneNo, @Param("createdAt") LocalDateTime createAt);

    //Using optional as user maybe or may not be present
    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    Optional<User> getUserByUsername(@Param("username") String username);


    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user WHERE created_at = :date", nativeQuery = true)
    List<User> getUsersByCreationDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET username = :newUsername WHERE username = :oldUsername", nativeQuery = true)
    void updateUsername(@Param("oldUsername") String oldUsername, @Param("newUsername") String newUsername); // making a separate method for updating username so that we dont have to share all details about the user

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET phone_number = :newPhoneNumber WHERE username = :username", nativeQuery = true)
    void updatePhoneNumber(@Param("username")String username, @Param("newPhoneNumber")String newPhoneNumber);// making a separate method for updating username so that we dont have to share all details about the user

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET first_name = :firstName, last_name = :lastName WHERE username = :username", nativeQuery = true)
    void updateName(@Param("username")String username, @Param("firstName")String firstName, @Param("lastName")String lastName);// making a separate method for updating username so that we dont have to share all details about the user

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET password = :newPassword WHERE username = :username", nativeQuery = true)
    void updatePassword(@Param("username") String username, @Param("newPassword")String newPassword);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user WHERE username = :username", nativeQuery = true)
    void deleteUserByUsername(@Param("username") String username);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user WHERE email = :email", nativeQuery = true)
    void deleteUserByEmail(@Param("email") String email);

    //// Below this are methods used only for testing

    @Profile("test")
    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User getUserByUsernameForTest(@Param("username") String username); ////used to return User entity instead of optional<User>
}
