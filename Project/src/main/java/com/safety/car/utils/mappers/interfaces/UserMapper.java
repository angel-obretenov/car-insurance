package com.safety.car.utils.mappers.interfaces;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.UserDetails;

public interface UserMapper {

    UserDetails fromDto(UserCreateDto userCreateDto);

    UserDetails fromDto(UserUpdateDto userUpdateDto);
}