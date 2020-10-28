package com.safety.car.controllers.mvc;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.models.entity.VerificationToken;
import com.safety.car.repositories.interfaces.VerificationTokenRepository;
import com.safety.car.services.interfaces.EmailService;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
@SessionAttributes({"carDto", "car"})
public class RegisterController {

    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsManager userDetailsManager;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(EmailService emailService, VerificationTokenRepository verificationTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                              UserDetailsManager userDetailsManager,
                              UserService userService,
                              UserMapper userMapper) {
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsManager = userDetailsManager;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String showRegisterPage(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute UserCreateDto userCreateDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreateDto", userCreateDto);
            model.addAttribute("error", "Username/password cannot be empty, please try again.");
            return "register";
        }

        if (userDetailsManager.userExists(userCreateDto.getEmail())) {
            model.addAttribute("userCreateDto", userCreateDto);
            model.addAttribute("error", "User with the same email already exists, please try again.");
            return "register";
        }

        if (!userCreateDto.getPassword().equals(userCreateDto.getConfirmPassword())) {
            model.addAttribute("userCreateDto", userCreateDto);
            model.addAttribute("error", "Passwords do not match, please try again.");
            return "register";
        }
        String username = userCreateDto.getEmail();
        String password = bCryptPasswordEncoder.encode(userCreateDto.getPassword());

        List<GrantedAuthority> authorities = userService.getAll().isEmpty()
                ? AuthorityUtils.createAuthorityList("ROLE_ADMIN")
                : AuthorityUtils.createAuthorityList("ROLE_USER");

        try {
            var newUser = new org.springframework.security.core.userdetails.User(username, password, authorities);
            userDetailsManager.createUser(newUser);

            UserDetails userDetails = userMapper.fromDto(userCreateDto);
            userService.create(userDetails);
            model.addAttribute("user", userDetails);

            VerificationToken verificationToken = new VerificationToken(userDetails);
            verificationTokenRepository.save(verificationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userDetails.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("Dear, " + userDetails.getFirstName() + " " + userDetails.getLastName() + "\n To confirm your account, please click here : "
                    + "http://localhost:8080/register/confirm-account?token=" + verificationToken.getToken()
                    + "\n Greetings, Insure Masters");

            emailService.sendEmail(mailMessage);

            return "successfulRegistration";
        } catch (IllegalArgumentException e) {
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token") String confirmationToken, Model model) {
        VerificationToken token;
        UserDetails user;
        try {
            token = verificationTokenRepository.findByVerificationToken(confirmationToken);
            user = userService.getByEmail(token.getUser().getEmail());

            user.setEnabled(true);
            userService.update(user);

            verificationTokenRepository.delete(token);
            model.addAttribute("message", "You have successfully confirmed your account!");
            return "accountVerified";
        } catch (NotFoundException | EntityNotFoundException e){
            model.addAttribute("message", "The link is invalid or broken");
            return "errorVerification";
        }
    }
}
