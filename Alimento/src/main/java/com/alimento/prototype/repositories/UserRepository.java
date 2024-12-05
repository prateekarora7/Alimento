package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository {

    //Using optional as user maybe or may not be present
    @Query(value = "SELECT * FROM User WHERE userId = :userId", nativeQuery = true)
    Optional<User> getUserByUserId(int userId);
}
