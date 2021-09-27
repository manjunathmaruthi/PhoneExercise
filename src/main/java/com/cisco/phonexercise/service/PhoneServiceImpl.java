package com.cisco.phonexercise.service;

import com.cisco.phonexercise.dao.PhoneDAO;
import com.cisco.phonexercise.dao.UserDAO;
import com.cisco.phonexercise.entity.Phone;
import com.cisco.phonexercise.exception.UserNotFoundException;
import com.cisco.phonexercise.exception.UserPhoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The class PhoneServiceImpl
 *
 * It provides implementation for addPhone, deleteUserPhone & findUserById
 */
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneDAO phoneDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * The method addPhone
     * It adds phone to a user, if user not found will throw UserNotFoundException
     * @param userId
     * @param phone
     * @return
     */
    @Override
    public Phone addPhone(UUID userId, Phone phone) {
        return userDAO.findById(userId)
                .map(user -> {
                    phone.setUser(user);
                    return phoneDAO.save(phone);
                }).orElseThrow(() -> new UserNotFoundException(
                        String.format("Unable to add Phone to user id: %s, since user not found",userId)));
    }

    /**
     * The method deleteUserPhone
     * It deletes user phone, if user not found will throw generic exception UserPhoneException
     * @param phoneId
     * @param userId
     * @return
     */
    @Override
    public ResponseEntity<?> deleteUserPhone(UUID phoneId, UUID userId) {
        return phoneDAO.findByIdAndUserId(phoneId, userId)
                .map(phone -> {
                    phoneDAO.delete(phone);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new UserPhoneException(
                        String.format("Phone not found with id: %s and user id: %s",phoneId,userId)));
    }

    /**
     * The method findByUserId
     * It finds all the phones of a user
     * @param userId
     * @return
     */
    @Override
    public List<Phone> findByUserId(UUID userId) {
        return phoneDAO.findByUserId(userId);
    }

}
