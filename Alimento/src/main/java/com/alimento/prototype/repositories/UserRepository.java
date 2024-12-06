package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Method register or save new user
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user (email, password, user_id, first_name, last_name, phone_number) " +
            "VALUES (:email, :password, :userId, :firstName, :lastName, :phoneNo)", nativeQuery = true)
    void saveUser(@Param("email") String email, @Param("password") String password, @Param("userId") String userId,
                  @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("phoneNo") String phoneNo);

    //Using optional as user maybe or may not be present
    @Query(value = "SELECT * FROM User WHERE user_id = :userId", nativeQuery = true)
    Optional<User> getUserByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM User WHERE email = :email", nativeQuery = true)
    Optional<User> getUserByEmail(@Param("email") String email);
}
