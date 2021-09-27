package com.cisco.phonexercise.service;

import com.cisco.phonexercise.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    User createUser(User user);

    void deleteUserById(UUID userId);

    List<User> getAllUsers();

    User getUserById(UUID userId);

    User updatePreferredPhoneNumber(UUID uuidUserId, String preferredPhoneNumber);
}
