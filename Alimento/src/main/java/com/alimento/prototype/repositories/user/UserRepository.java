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
    @Query(value = "INSERT INTO user (email, password, user_id, first_name, last_name, phone_number, created_at) " +
            "VALUES (:email, :password, :userId, :firstName, :lastName, :phoneNo, :createdAt)", nativeQuery = true)
    void saveUser(@Param("email") String email, @Param("password") String password, @Param("userId") String userId,
                  @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNo") String phoneNo, @Param("createdAt") LocalDateTime createAt);

    //Using optional as user maybe or may not be present
    @Query(value = "SELECT * FROM user WHERE user_id = :userId", nativeQuery = true)
    Optional<User> getUserByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> getUserByEmail(String email);
}
