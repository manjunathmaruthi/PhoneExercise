package com.cisco.phonexercise.dao;

import com.cisco.phonexercise.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneDAO extends JpaRepository<Phone, UUID> {

    Optional<Phone> findByIdAndUserId(UUID id, UUID userId);

    List<Phone> findByUserId(UUID userId);
}
