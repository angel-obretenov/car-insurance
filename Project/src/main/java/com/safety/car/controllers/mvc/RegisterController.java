package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsManager userDetailsManager;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
                              UserDetailsManager userDetailsManager,
                              UserService userService,
                              UserMapper userMapper) {
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

        var newUser = new org.springframework.security.core.userdetails.User(username, password, authorities);
        userDetailsManager.createUser(newUser);

        UserDetails userDetails = userMapper.fromDto(userCreateDto);
        userService.create(userDetails);

        return "redirect:/login";
    }
}
