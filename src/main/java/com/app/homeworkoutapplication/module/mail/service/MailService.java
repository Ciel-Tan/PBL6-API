package com.app.homeworkoutapplication.module.mail.service;

import com.app.homeworkoutapplication.module.user.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${app.activation.url}")
    private String activationUrl;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(User user) {
        String subject = "Account Activation";
        String activationLink = activationUrl + "?email=" + user.getEmail();
        String message = "Click on the link to activate your account: " + activationLink;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
