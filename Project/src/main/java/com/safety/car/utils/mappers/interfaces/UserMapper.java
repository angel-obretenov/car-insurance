package com.safety.car.utils.mappers.interfaces;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserMapper {

    UserDetails fromDto(UserCreateDto userCreateDto);

    UserDetails fromDto(UserUpdateDto userUpdateDto);

    void enableSpringUser(String email);

    void createSpringUser(String username, String password, List<GrantedAuthority> authorities);
}