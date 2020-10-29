package com.safety.car.services.interfaces;

import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.models.entity.VerificationToken;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage email);

    void sendVerificationEmail(UserDetails userDetails, VerificationToken verificationToken);

    void sendPolicyStatusEmail(PolicyRequest policyRequest);

}
