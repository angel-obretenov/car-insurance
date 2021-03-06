package com.safety.car.services.interfaces;

import com.safety.car.models.entity.UserDetails;

import java.util.List;

public interface UserService {

    List<UserDetails> getAll();

    UserDetails getById(int id);

    UserDetails getByEmail(String email);

    void create(UserDetails userDetails);

    void update(UserDetails userDetails);

    boolean emailExists(UserDetails userDetails);
}
