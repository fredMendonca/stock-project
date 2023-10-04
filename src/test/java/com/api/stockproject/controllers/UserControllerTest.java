package com.api.stockproject.controllers;

import com.api.stockproject.models.User;
import com.api.stockproject.services.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {
    UserController userController = mock(UserController.class);
    @Test
    void return_Saved_User() {
        User user = populateUser();
        when(userController.createUser(user)).thenReturn(user);
        assertNotNull(user);
    }
    @Test
    void valid_saved_User() {
        String userTestname = "Joao";
        User user = populateUser();
        when(userController.createUser(user)).thenReturn(user);
        assertEquals(userTestname, user.getName());
    }
    private User populateUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Joao");
        user.setEmail("Joao@gmail.com");
        return user;
    }
}