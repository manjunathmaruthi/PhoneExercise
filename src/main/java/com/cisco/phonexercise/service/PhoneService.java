package com.cisco.phonexercise.service;

import com.cisco.phonexercise.entity.Phone;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PhoneService {

    Phone addPhone(UUID userId, Phone phone);

    ResponseEntity<?> deleteUserPhone(UUID phoneId, UUID userId);

    List<Phone> findByUserId(UUID userId);
}
