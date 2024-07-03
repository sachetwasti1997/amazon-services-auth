package com.sachet.amazon_services_auth.repository;

import com.sachet.amazon_services_auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT * FROM user WHERE email = ?1")
    Optional<User> findByEmail(String email);
}
