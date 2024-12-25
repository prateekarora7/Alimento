package com.alimento.prototype.repositories.user;

import com.alimento.prototype.entities.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User getUserByUsernameForTest(@Param("username") String username);
}
