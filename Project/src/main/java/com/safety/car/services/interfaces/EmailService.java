package com.safety.car.services.interfaces;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage email);

}
