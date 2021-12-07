package com.pascal7.ingre_api_mono.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String mailTo, String subject, String messageBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(messageBody);
        javaMailSender.send(message);
    }
}
