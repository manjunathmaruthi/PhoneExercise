package com.cisco.phonexercise.controller;

import com.cisco.phonexercise.entity.Phone;
import com.cisco.phonexercise.service.PhoneService;
import com.cisco.phonexercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The class PhoneController
 *
 * This controller is manages
 *  - addPhoneToUser
 *  - deleteUserPhone
 *  - getAllPhonesbyUserId
 */
@RestController
@RequestMapping("api/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserService userService;

    /**
     * Adds phone to user
     * @param userId
     * @param phone
     * @return
     */
    @PostMapping("/users/{userId}")
    public Phone addPhoneToUser(@PathVariable String userId, @RequestBody Phone phone){
        UUID uuid = UUID.fromString(userId);
        return phoneService.addPhone(uuid,phone);
    }

    /**
     * Delets user phone
     * @param phoneId
     * @param userId
     * @return
     */
    @DeleteMapping("/{phoneId}/users/{userId}")
    public ResponseEntity<?> deleteUserPhone(@PathVariable String phoneId,
                                          @PathVariable String userId) {
        UUID phoneUUId = UUID.fromString(phoneId);
        UUID userUUId = UUID.fromString(userId);
        return phoneService.deleteUserPhone(phoneUUId,userUUId);
    }

    /**
     * Gets all Phones of the user
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public List<Phone> getAllPhonesbyUserId(@PathVariable String userId) {
        UUID userUUId = UUID.fromString(userId);
        return phoneService.findByUserId(userUUId);
    }
}
