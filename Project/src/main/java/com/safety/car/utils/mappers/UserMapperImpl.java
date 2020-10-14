package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final AddressRepository addressRepository;

    @Autowired
    public UserMapperImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public UserDetails fromDto(UserCreateDto userCreateDto) {
        UserDetails user = new UserDetails();

        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());

        addressRepository.createIfNotExist(userCreateDto.getAddressName());
        Address address = addressRepository.findByName(userCreateDto.getAddressName());
        user.setAddress(address);

        return user;
    }

    @Override
    public UserDetails fromDto(UserUpdateDto userUpdateDto) {
        var user = new UserDetails();

        user.setId(userUpdateDto.getId());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        addressRepository.createIfNotExist(userUpdateDto.getAddressName());
        Address address = addressRepository.findByName(userUpdateDto.getAddressName());
        user.setAddress(address);

        return user;
    }

}
