package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.UserDetailsService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final AddressRepository addressRepository;
    private final UserDetailsService userService;

    @Autowired
    public UserMapperImpl(AddressRepository addressRepository, UserDetailsService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails fromDto(UserCreateDto userCreateDto) {
        var user = new UserDetails();

        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());

        addressRepository.checkIfNeedsToBeSaved(userCreateDto.getAddressName());
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
        addressRepository.checkIfNeedsToBeSaved(userUpdateDto.getAddressName());
        Address address = addressRepository.findByName(userUpdateDto.getAddressName());
        user.setAddress(address);

        return user;
    }
}
