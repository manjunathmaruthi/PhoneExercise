package com.cisco.phonexercise.controller;

import com.cisco.phonexercise.entity.User;
import com.cisco.phonexercise.jwt.model.JwtRequest;
import com.cisco.phonexercise.jwt.model.JwtResponse;
import com.cisco.phonexercise.service.UserService;
import com.cisco.phonexercise.utility.JWTUtility;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The class UserController manages
 *  - createUser
 *  - deleteUserById
 *  - getAllUsers
 *  - updatePreferredPhoneNumber
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Creates user
     * @param user
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        logger.info("Request received to create user: {}",user);
        User newUser = userService.createUser(user);
        logger.info("User created successfully: {}",user);
        return newUser;
    }

    /**
     * Deletes user by userId
     * @param userId
     */
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId){
        logger.info("Request received to delete userId :{}",userId);
        UUID uuidUserId = UUID.fromString(userId);
        userService.deleteUserById(uuidUserId);
    }

    /**
     * Gets all users
     * @return
     */
    @GetMapping
    public List<User> getAllUsers(){
        logger.info("Request received to get all users...");
        List<User> users = userService.getAllUsers();
        logger.info("Sucessfully fetched all users...");
        return users;
    }

    /**
     * Updates user PreferredPhone Number
     * @param userId
     * @param preferredPhoneNumber
     * @return
     */
    @PatchMapping("/{userId}")
    public User updatePreferredPhoneNumber(@PathVariable String userId, @RequestBody ObjectNode preferredPhoneNumber){
        logger.info("Request received to update preferredphonenumber : {} for userId : {}",preferredPhoneNumber,userId);
        UUID uuidUserId = UUID.fromString(userId);
        User user= userService.updatePreferredPhoneNumber(uuidUserId,preferredPhoneNumber.get("preferredPhoneNumber").asText());
        logger.info("Preffered phonenumber : {} updated successfully for userId; {}",preferredPhoneNumber.asText(),userId);
        return user;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token);
    }
}
