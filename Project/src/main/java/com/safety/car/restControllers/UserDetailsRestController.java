package com.safety.car.restControllers;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.UserDetailsService;
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
    private final UserDetailsService userService;

    @Autowired
    public UserDetailsRestController(UserMapper userMapper, UserDetailsService userService) {
        this.userMapper = userMapper;
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
    public UserDetails create(@RequestBody UserCreateDto userCreateDto) {
        try {
            UserDetails userDetails = userMapper.fromDto(userCreateDto);
            userService.create(userDetails);
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
            userService.update(userDetails);
            return userDetails;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }
}