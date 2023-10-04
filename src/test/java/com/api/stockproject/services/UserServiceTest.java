package com.api.stockproject.services;

import com.api.stockproject.models.User;
import com.api.stockproject.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    @Test
    void return_Saved_User() {
        User user = populateUser();
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(user);
    }
    @Test
    void valid_saved_User() {
        String userTestName = "Joao";
        User user = populateUser();
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(userTestName, user.getName());
    }
    private User populateUser(){
        User user = new User();
        user.setId(1L);
        user.setName("Joao");
        user.setEmail("Joao@gmail.com");
        return user;
    }
}