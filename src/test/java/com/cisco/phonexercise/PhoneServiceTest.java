package com.cisco.phonexercise;

import com.cisco.phonexercise.dao.PhoneDAO;
import com.cisco.phonexercise.dao.UserDAO;
import com.cisco.phonexercise.entity.Phone;
import com.cisco.phonexercise.entity.User;
import com.cisco.phonexercise.service.PhoneServiceImpl;
import com.cisco.phonexercise.utility.PhoneModelEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PhoneServiceTest {

    @InjectMocks
    PhoneServiceImpl phoneService;

    @Mock
    PhoneDAO mockedPhoneDAO;

    @Mock
    UserDAO mockedUserDAO;

   /* @Test
    public void testAddPhone(){
        //setup
        UUID uuid = UUID.fromString("f5f94b21-0783-4f4b-b309-f6ea3325db46");
        Optional<User> userOptional = Optional.of(createOneUser());
        Phone phone =createOnePhone();
        given(mockedUserDAO.findById(uuid).map(user -> {
            phone.setUser(user);
            return mockedPhoneDAO.save(phone);})).willReturn(Optional.of(phone));

        //when(mockedPhoneDAO.save(phone)).thenReturn(phone);

        //invoke
        phoneService.addPhone(uuid,createOnePhone());

        //verify
        assertEquals(phone,phone);
        verify(mockedPhoneDAO, times(1)).save(phone);
        verify(mockedUserDAO, times(1)).findById(uuid);
    }*/


    @Test
    public void testFindByUserId(){

        //setup
        UUID uuid = UUID.fromString("f5f94b21-0783-4f4b-b309-f6ea3325db46");
        List<Phone> phoneList = Arrays.asList(createOnePhone());
        when(mockedPhoneDAO.findByUserId(uuid)).thenReturn(phoneList);

        //invoke
        phoneService.findByUserId(uuid);

        //verify
        assertEquals(phoneList,phoneService.findByUserId(uuid));
    }


    private User createOneUser() {
        User user = new User();
        user.setUserName("John");
        user.setPreferredPhoneNumber("+353812345");
        user.setEmailAddress("abc@test.com");
        user.setPassword("test123");
        return user;
    }

    private Phone createOnePhone() {
        Phone phone = new Phone();
        phone.setUser(createOneUser());
        phone.setPhoneModel(PhoneModelEnum.SOFT_PHONE);
        phone.setPhoneNumber("+353812345");
        phone.setPhoneName("SOFTPHONE");
        return phone;
    }
}
