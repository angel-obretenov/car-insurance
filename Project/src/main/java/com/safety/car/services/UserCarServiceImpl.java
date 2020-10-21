package com.safety.car.services;

import com.safety.car.models.entity.UserCar;
import com.safety.car.repositories.interfaces.UserCarRepository;
import com.safety.car.services.interfaces.UserCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCarServiceImpl implements UserCarService {
    private final UserCarRepository userCarRepository;

    @Autowired
    public UserCarServiceImpl(UserCarRepository userCarRepository) {
        this.userCarRepository = userCarRepository;
    }

    @Override
    public UserCar getByUserId(int id) {
        return userCarRepository.getByUserId(id);
    }

    @Override
    public void create(UserCar userCar) {
        userCarRepository.create(userCar);
    }

    @Override
    public void update(UserCar userCar) {
        userCarRepository.create(userCar);
    }
}
