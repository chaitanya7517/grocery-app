package com.example.user_service.utilities;

import com.example.user_service.entities.User;
import com.example.user_service.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Component
public class AuthHelper {

    @Autowired
    UserRepo userRepo;

    public String generateSecureApiKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public Long generateExpiryTime() {
        return System.currentTimeMillis() + (1 * 1 * 60 * 1000);   /* (hr * mn * sec* milisec) */
    }

    public boolean isAuthenticated(String apiKey) {
        try {
            List<User> existingUser = userRepo.findByApiKey(apiKey);
            if (existingUser.isEmpty()) {
                return false;
            }
            User user = existingUser.get(0);
            if (user.getExpiryTime() < System.currentTimeMillis()) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
