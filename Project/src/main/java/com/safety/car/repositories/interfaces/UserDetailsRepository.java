package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.UserDetails;

import java.util.List;

public interface UserDetailsRepository {

    UserDetails getById(int id);

    List<UserDetails> getAll();

    void create(UserDetails userDetails);

    void update(UserDetails userDetails);
}
