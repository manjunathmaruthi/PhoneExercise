package com.cisco.phonexercise;

import com.cisco.phonexercise.dao.UserDAO;
import com.cisco.phonexercise.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static java.util.Arrays.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDAO mockedUserDAO;

    @Autowired
    private JacksonTester<User> jsonUser;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUsers() throws Exception {
        //setup
        given(mockedUserDAO.findAll())
                .willReturn(asList(createUserWithUUID()));

        // invoke and test
       mockMvc.perform(
                        get("/api/users")
                                .with(csrf())
                                .with(user("John"))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())

                .andExpect(content().json("[{'id':'f5f94b21-0783-4f4b-b309-f6ea3325db46'," +
                        "'userName':'John', 'preferredPhoneNumber':'+353812345', " +
                        "'emailAddress':'abc@test.com', 'password':'test123'}]"));
    }

    @Test
    public void testCreateUser() throws Exception {
        //setup
        given(mockedUserDAO.save(createUserWithOutUUID()))
                .willReturn(createUserWithUUID());

        // invoke
        mockMvc.perform(
                        post("/api/users")
                                .content(objectMapper.writeValueAsString(createUserWithOutUUID()))
                                .contentType(MediaType.APPLICATION_JSON))
                //test
                .andExpect(status().isCreated());
    }

    //TODO: similarly we can test for other endpoints

    private User createUserWithOutUUID() {
        User user = new User();
        user.setId(UUID.fromString("f5f94b21-0783-4f4b-b309-f6ea3325db46"));
        user.setUserName("John");
        user.setPreferredPhoneNumber("+353812345");
        user.setEmailAddress("abc@test.com");
        user.setPassword("test123");
        return user;
    }

    private User createUserWithUUID(){
        User user = createUserWithOutUUID();
        user.setId(UUID.fromString("f5f94b21-0783-4f4b-b309-f6ea3325db46"));
        return user;
    }
}
