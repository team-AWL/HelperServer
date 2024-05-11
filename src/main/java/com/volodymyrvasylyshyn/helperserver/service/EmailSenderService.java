package com.volodymyrvasylyshyn.helperserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    private final Environment env;

    public EmailSenderService(Environment env) {
        this.env = env;
    }

    public void sendMail(String toMail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);

    }

}
