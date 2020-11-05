package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//@Service()
public class MailServiceImpl implements MailService {

    private static final String SUBJECT = "Password change";
    private static final String TEXT_TEMPLATE = "Your password for \"Lucky Paw\" online market is: %s.";

    private final JavaMailSender mailSender;

//    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(String email, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(String.format(TEXT_TEMPLATE, message));
        mailSender.send(mailMessage);
    }
}
