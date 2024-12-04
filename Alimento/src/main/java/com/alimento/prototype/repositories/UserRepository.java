package com.alimento.prototype.repositories;

import com.alimento.prototype.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository {

    @Query(value = "SELECT * FROM User WHERE userId = :userId", nativeQuery = true)
    User getUserByUserId(int userId);
}
