package com.example.user_service.services;

import com.example.user_service.entities.User;
import com.example.user_service.repos.UserRepo;
import com.example.user_service.utilities.AuthHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthHelper authHelper;

    public String saveUser(HashMap<String,String> reqDTO) {
        try {
            List<User> existingUser = userRepo.findByUsername(reqDTO.get("username"));
            if(existingUser.isEmpty()) {
                if(reqDTO.get("password").equals(reqDTO.get("confirmPassword"))) {
                    User user = new User();
                    user.setUsername(reqDTO.get("username"));
                    user.setPassword(reqDTO.get("password"));
                    return userRepo.save(user).getApiKey();
                }
                else {
                    return "password and confirmPassword not matching";
                }
            }
            else {
                return "Please try with some other username";
            }
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String login(HashMap<String, String> reqDTO) {
        try {
            List<User> existingUser = userRepo.findByUsername(reqDTO.get("username"));

            if (existingUser.isEmpty() || !existingUser.get(0).getPassword().equals(reqDTO.get("password"))) {
                return "Incorrect username or password";
            }

            User user = existingUser.get(0);
            user.setApiKey(authHelper.generateSecureApiKey());
            user.setExpiryTime(authHelper.generateExpiryTime());

            userRepo.save(user);

            return user.getApiKey();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String logout(String apiKey) {
        try {
            List<User> existingUser = userRepo.findByApiKey(apiKey);
            if(existingUser.isEmpty()) {
                return "User is not present with this api key";
            }
            else {
                User user = existingUser.get(0);
                user.setExpiryTime(System.currentTimeMillis() - 1);
                userRepo.save(user);
                return "Logout successfully";
            }
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }


}
