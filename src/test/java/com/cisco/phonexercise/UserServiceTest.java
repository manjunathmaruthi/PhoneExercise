package com.cisco.phonexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.cisco.phonexercise.dao.UserDAO;
import com.cisco.phonexercise.entity.User;
import com.cisco.phonexercise.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDAO mockedUserDAO;


    @Test
    public void testCreateUser(){
        //setup
        User user = createOneUser();
        when(mockedUserDAO.save(user)).thenReturn(user);

        //invoke
        userService.createUser(user);

        //verify
        assertEquals(user,user);
        verify(mockedUserDAO, times(1)).save(user);
    }

    @Test
    public void testGetAllUsers(){
        //setup
        User expectedUser = createOneUser();
        List<User> expectedUserList = Arrays.asList(expectedUser);
        when(mockedUserDAO.findAll()).thenReturn(Arrays.asList(expectedUser));

        //invoke
        List<User> acutalUserList = userService.getAllUsers();

        //verify
        assertEquals(expectedUserList, acutalUserList);
        verify(mockedUserDAO, times(1)).findAll();
    }

    //TODO: similarly we can add tests for others

    private User createOneUser() {
        User user = new User();
        user.setUserName("John");
        user.setPreferredPhoneNumber("+353812345");
        user.setEmailAddress("abc@test.com");
        user.setPassword("test123");
        return user;
    }
}
