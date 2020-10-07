package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.CarService;
import com.safety.car.services.interfaces.GenericUtilityService;
import com.safety.car.services.interfaces.UserDetailsService;
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
public class PolicyHelper {

    private final GenericUtilityService<Address> addressService;
    private final UserDetailsService userService;
    private final CarService carService;

    @Autowired
    public PolicyHelper(GenericUtilityService<Address> addressService, UserDetailsService userService, CarService carService) {
        this.addressService = addressService;
        this.userService = userService;
        this.carService = carService;
    }

    public PolicyDetails toPolicyDetails(PolicyDetailsDto dto) {

        PolicyDetails policyDetails = new PolicyDetails();
        //TODO
        Address address = addressService.getById(dto.getAddressId());
        UserDetails user = userService.getById(dto.getUserDetailsId());
        Car car = carService.getById(dto.getCarId());

        policyDetails.setStartDate(dto.getStartDate());
        policyDetails.setEndDate(dto.getEndDate());
//        policyDetails.setPicture();
        policyDetails.setPhoneNumber(dto.getPhoneNumber());
        policyDetails.setEmail(dto.getEmail());
        policyDetails.setAddress(address);
        policyDetails.setUser(user);
        policyDetails.setCar(car);

        return policyDetails;
    }

    public static void pictureSaver(PolicyDetailsDto dto) {
        MultipartFile photo = dto.getPicture();
        Path path = Paths.get("uploads/");

        try {
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream, path.resolve(photo.getOriginalFilename().toLowerCase()),
                    StandardCopyOption.REPLACE_EXISTING);
            dto.setPath(photo.getOriginalFilename().toLowerCase());
        } catch (IOException e) {
            dto.setPath("corona.png");
            e.printStackTrace();
        }
    }
}
