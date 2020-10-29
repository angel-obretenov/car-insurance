package com.safety.car.services;

import com.safety.car.models.dto.rest.SearchPoliciesDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.models.entity.VerificationToken;
import com.safety.car.services.interfaces.EmailService;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;
    private final PolicyDetailsService policyDetailsService;
    private final PolicyRequestService policyRequestService;

    @Autowired
    public EmailServiceImpl(@Qualifier("getJavaMailSender") JavaMailSender javaMailSender,
                            UserService userService,
                            PolicyDetailsService policyDetailsService,
                            PolicyRequestService policyRequestService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
        this.policyDetailsService = policyDetailsService;
        this.policyRequestService = policyRequestService;
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

    public void sendPolicyStatusEmail(PolicyRequest policyRequest) {

        UserDetails userDetails = policyRequest.getUserDetails();
        PolicyDetails policyDetails = policyRequest.getPolicyDetails();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDetails.getEmail());
        if (policyRequest.getApproved()){
            mailMessage.setSubject("Details on your policy request");
            mailMessage.setText("Dear, " + userDetails.getFirstName() + " " + userDetails.getLastName() +
                    "\n We have accepted your policy request with Ticket N:" + policyRequest.getId() + "/ for" +
                    "\n Car with brand " + policyDetails.getCar().getBrand().getName()
                    +" and with model " + policyDetails.getCar().getModel().getName()
                    + "\n Greetings, Insure Masters");
        } else {
            mailMessage.setSubject("Details on your policy request");
            mailMessage.setText("Dear, " + userDetails.getFirstName() + " " + userDetails.getLastName() +
                    "\n We have rejected your policy request with Ticket N:" + policyRequest.getId() + "/ for" +
                    "\n Car with brand " + policyDetails.getCar().getBrand().getName()
                    +" and with model " + policyDetails.getCar().getModel().getName()
                    +"\n Since you don't meet our requirements"
                    + "\n Greetings, Insure Masters");
        }
        sendEmail(mailMessage);
    }
}