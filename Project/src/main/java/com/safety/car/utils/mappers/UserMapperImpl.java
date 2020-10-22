package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.AddressService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final AddressService addressService;

    @Autowired
    public UserMapperImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public UserDetails fromDto(UserCreateDto userCreateDto) {
        UserDetails user = new UserDetails();

        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());

        addressService.createIfNotExist(userCreateDto.getAddressName());
        Address address = addressService.findByName(userCreateDto.getAddressName());
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
        addressService.createIfNotExist(userUpdateDto.getAddressName());
        Address address = addressService.findByName(userUpdateDto.getAddressName());
        user.setAddress(address);

        return user;
    }

}
