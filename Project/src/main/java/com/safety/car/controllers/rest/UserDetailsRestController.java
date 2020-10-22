package com.safety.car.controllers.rest;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserDetailsRestController {

    private final UserMapper userMapper;
    private final UserService userDetailsService;

    @Autowired
    public UserDetailsRestController(UserMapper userMapper, UserService userDetailsService) {
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<UserDetails> getAll() {
        return userDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public UserDetails getById(@PathVariable int id) {
        try {
            return userDetailsService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @PostMapping
    public UserDetails create(@RequestBody UserCreateDto userCreateDto) {
        try {
            UserDetails userDetails = userMapper.fromDto(userCreateDto);
            userDetailsService.create(userDetails);
            return userDetails;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        } catch (DuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    e.getMessage());
        }
    }

    @PutMapping
    public UserDetails update(@RequestBody UserUpdateDto userUpdateDto) {
        try {
            UserDetails userDetails = userMapper.fromDto(userUpdateDto);
            userDetailsService.update(userDetails);
            return userDetails;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }
}