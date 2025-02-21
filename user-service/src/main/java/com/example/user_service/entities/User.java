package com.example.user_service.entities;

import com.example.user_service.utilities.AuthHelper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String username;

    String password;

    String apiKey;

    Long expiryTime;

    @PrePersist
    public void generateApiKeyAndExpiry() {
        AuthHelper authHelper = new AuthHelper();
        this.apiKey = authHelper.generateSecureApiKey();
        this.expiryTime = authHelper.generateExpiryTime();
    }

}