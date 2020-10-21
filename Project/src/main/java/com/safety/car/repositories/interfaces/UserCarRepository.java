package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.UserCar;

public interface UserCarRepository {

    UserCar getByUserId(int id);

    void create(UserCar userCar);

    void update(UserCar userCar);

}
