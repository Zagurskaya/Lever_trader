package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final String TITLE_LINK = "Your confirmation link";
    private static final String TITLE_CODE = "Your confirmation code";
    private static final String TEXT_LINK = "Your confirmation link: http://localhost:8080/api/auth/confirm?token=%s ";
    private static final String TEXT_CODE = "Your confirmation code:  %s";

    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendLinkToMail(String email, String link) {
        sendToMail(email, link, TITLE_LINK, TEXT_LINK);
    }

    @Override
    public void sendCodeToMail(String email, String activationСode) {
        sendToMail(email, activationСode, TITLE_CODE, TEXT_CODE);
    }

    private void sendToMail(String email, String message, String title, String textLetter) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(title);
        mailMessage.setText(String.format(textLetter, message));
        mailSender.send(mailMessage);
    }
}
