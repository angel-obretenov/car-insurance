package com.safety.car.services;

import com.safety.car.exceptions.DuplicateException;
import com.safety.car.exceptions.UnauthorizedException;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.UserDetailsRepository;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.safety.car.utils.constants.service.ServiceConstants.EMAIL_CHANGE_REJECTION;
import static com.safety.car.utils.constants.service.ServiceConstants.USER_EMAIL_EXISTS;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDetailsRepository userRepository) {
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
                    format(USER_EMAIL_EXISTS, userDetails.getEmail()));
        }

        userRepository.create(userDetails);
    }

    @Override
    public void update(UserDetails userDetails) {
        UserDetails oldUser = getById(userDetails.getId());

        if (!oldUser.getEmail().equals(userDetails.getEmail())) {
            throw new UnauthorizedException(EMAIL_CHANGE_REJECTION);
        }

        userRepository.update(userDetails);
    }
}