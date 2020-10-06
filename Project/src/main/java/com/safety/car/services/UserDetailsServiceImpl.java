package com.safety.car.services;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.UnauthorizedException;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.UserDetailsRepository;
import com.safety.car.services.interfaces.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDetails> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDetails getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void create(UserDetails userDetails) {
        if (userRepository.emailExists(userDetails.getEmail())) {
            throw new DuplicateException(
                    format("User with email: %s, already exists", userDetails.getEmail()));
        }

        userRepository.create(userDetails);
    }

    @Override
    public void update(UserDetails userDetails) {
        UserDetails oldUser = getById(userDetails.getId());

        if (!oldUser.getEmail().equals(userDetails.getEmail())) {
            throw new UnauthorizedException("Cannot change email of the user!");
        }

        userRepository.update(userDetails);
    }
}