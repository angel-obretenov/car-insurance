package com.safety.car.services;

import com.safety.car.models.entity.UserDetails;
import com.safety.car.models.entity.VerificationToken;
import com.safety.car.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(@Qualifier("getJavaMailSender") JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void sendVerificationEmail(UserDetails userDetails, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("Dear, " + userDetails.getFirstName() + " " + userDetails.getLastName() + "\n To confirm your account, please click here : "
                + "http://localhost:8080/register/confirm-account?token=" + verificationToken.getToken()
                + "\n Greetings, Insure Masters");

        sendEmail(mailMessage);
    }
}