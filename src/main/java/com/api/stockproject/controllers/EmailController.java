package com.api.stockproject.controllers;

import com.api.stockproject.services.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    private final JavaMailSender mailSender;
    private final EmailService emailService;
    private static final Logger logger = LogManager.getLogger(EmailController.class);
    private static final String EMAIL_SEND_CONFIRMATION = "Email successfully sent";
    private static final String EMAIL_SEND_ERROR = "Error sending email";

    public EmailController(JavaMailSender mailSender, EmailService emailService) {
        this.mailSender = mailSender;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/email-send/{userEmail}", method = RequestMethod.GET)
    public String sendMail(@PathVariable String userEmail) {

        try {
            MimeMessage mail = mailSender.createMimeMessage();

            mailSender.send(emailService.sendEmail(mail, userEmail));
            logger.info("Email successfully sent.");
            return EMAIL_SEND_CONFIRMATION;
        } catch (Exception e) {
            logger.error("Error sending email!");
            e.printStackTrace();
            return EMAIL_SEND_ERROR;
        }
    }
}