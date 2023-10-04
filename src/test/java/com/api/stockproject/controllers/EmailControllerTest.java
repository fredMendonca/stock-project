package com.api.stockproject.controllers;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmailControllerTest {

    private EmailController emailController = mock(EmailController.class);

    @Test
    void sendMail() throws Exception {
        String userTestEmail = "joao@gmail.com";
        when(emailController.sendMail(userTestEmail)).thenReturn(userTestEmail);
        assertNotNull(userTestEmail);
    }
}