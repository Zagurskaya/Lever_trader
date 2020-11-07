package com.gmail.zagurskaya.service;

public interface MailService {

    void sendLinkToMail(String email, String link);

    void sendCodeToMail(String email, String activationСode);
}
