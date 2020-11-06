package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final String SUBJECT = "Your confirmation link";
    private static final String TEXT_LINK = "Your confirmation link: http://localhost:8080/api/auth/confirm?token=%s ";

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendLinkToMail(String email, String link) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(String.format(TEXT_LINK, link));
        mailSender.send(mailMessage);
    }
}
