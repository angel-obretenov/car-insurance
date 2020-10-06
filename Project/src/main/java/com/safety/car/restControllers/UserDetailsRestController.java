package com.safety.car.restControllers;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserDetailsRestController {

    private final UserDetailsService userService;

    @Autowired
    public UserDetailsRestController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDetails> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDetails getById(@PathVariable int id) {
        try {
            return userService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @PostMapping
    public UserDetails create(UserDetails userDetails) {
        try {
            userService.create(userDetails);
            return userDetails;
        } catch (DuplicateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @PutMapping
    public UserDetails update(UserDetails userDetails) {
        try {
            userService.update(userDetails);
            return userDetails;
        } catch (DuplicateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }
}