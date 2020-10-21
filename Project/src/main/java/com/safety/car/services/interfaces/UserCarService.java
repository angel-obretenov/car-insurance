package com.safety.car.services.interfaces;

import com.safety.car.models.entity.UserCar;

public interface UserCarService {

    UserCar getByUserId(int id);

    void create(UserCar userCar);

    void update(UserCar userCar);

}
