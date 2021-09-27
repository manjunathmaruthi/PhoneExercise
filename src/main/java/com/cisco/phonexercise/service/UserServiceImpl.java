package com.cisco.phonexercise.service;

import com.cisco.phonexercise.dao.UserDAO;
import com.cisco.phonexercise.entity.User;
import com.cisco.phonexercise.exception.UserNotFoundException;
import com.cisco.phonexercise.exception.UserPhoneException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The class UserServiceImpl
 *
 * It provides implementation for createUser, deleteUserById,
 * getAllUsers, getUserById & updatePreferredPhoneNumber
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public User createUser(User user) {
        return userDAO.save(user);
    }

    /**
     * The method deleteUserById
     * It delets a user by id, if user not found will throw UserNotFoundException
     * @param userId
     */
    @Override
    public void deleteUserById(UUID userId) {
        userDAO.findById(userId).map(user -> {
            logger.debug("User found to delete userId: {}", userId);
           userDAO.deleteById(userId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new UserNotFoundException(String.format("Unable to delete user id: %s, since not found", userId)));

    }

    /**
     * The method getAllUsers
     * It gets all users
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    /**
     * The method getUserById
     * It gets a user by id
     * @param userId
     * @return
     */
    @Override
    public User getUserById(UUID userId) {
      return userDAO.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found exception"));
    }

    /**
     * The method updatePreferredPhoneNumber
     *
     * It updates preferred phone number of a user
     * @param userId
     * @param preferredPhoneNumber
     * @return
     */
    @Override
    public User updatePreferredPhoneNumber(UUID userId, String preferredPhoneNumber) {
        return userDAO.findById(userId)
                .map(user -> {
                    user.setPreferredPhoneNumber(preferredPhoneNumber);
                    return userDAO.save(user);
                })
                .orElseThrow(()-> new UserPhoneException(
                        String.format("Unable to update preferredPhoneNumber: %s for the userId: %s",preferredPhoneNumber,userId)));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(s);
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
