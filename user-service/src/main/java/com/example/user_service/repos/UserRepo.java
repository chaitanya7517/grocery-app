package com.example.user_service.repos;

import com.example.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);
    List<User> findByApiKey(String apiKey);
}
