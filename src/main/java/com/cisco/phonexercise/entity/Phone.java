package com.cisco.phonexercise.entity;

import com.cisco.phonexercise.utility.PhoneModelEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

/**
 * Phone entity
 */
@Entity(name = "PHONE")
public class Phone {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "PHONE_NAME", nullable = false)
    private String phoneName;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "PHONE_MODEL", nullable = false)
    private PhoneModelEnum phoneModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneModelEnum getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(PhoneModelEnum phoneModel) {
        this.phoneModel = phoneModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneName='" + phoneName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneModel=" + phoneModel +
                '}';
    }
}
