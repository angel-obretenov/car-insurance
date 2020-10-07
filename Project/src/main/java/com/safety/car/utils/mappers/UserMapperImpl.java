package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.dto.rest.UserCreateDto;
import com.safety.car.models.dto.rest.UserUpdateDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.CarService;
import com.safety.car.services.interfaces.GenericUtilityService;
import com.safety.car.services.interfaces.UserDetailsService;
import com.safety.car.utils.mappers.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class UserMapperImpl implements UserMapper {

    //TODO address service and car
    private final AddressRepository addressRepository;
    private final GenericUtilityService<Address> addressService;
    private final UserDetailsService userService;
    private final CarService carService;

    @Autowired
    public UserMapperImpl(AddressRepository addressRepository, GenericUtilityService<Address> addressService, UserDetailsService userService, CarService carService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public UserDetails fromDto(UserCreateDto userCreateDto) {
        UserDetails user = new UserDetails();

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
