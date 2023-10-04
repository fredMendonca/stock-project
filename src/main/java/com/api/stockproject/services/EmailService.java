package com.api.stockproject.services;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    public MimeMessage SendEmail(MimeMessage mail, String userEmail) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper( mail );
        helper.setTo( userEmail );
        helper.setSubject( "Order shipping confirmation" );
        helper.setText("<p>Hello, your order has been completed.</p>", true);
        return helper.getMimeMessage();
    }
}
