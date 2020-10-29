package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.AddressService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserDetailsManager userDetailsManager;

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

    public void enableUser(String email){

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        org.springframework.security.core.userdetails.UserDetails updateUser = userDetailsManager.loadUserByUsername(email);
        updateUser = new org.springframework.security.core.userdetails.User(
                updateUser.getUsername(),
                updateUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                updateUser.getAuthorities());

        userDetailsManager.updateUser(updateUser);
    }
}
