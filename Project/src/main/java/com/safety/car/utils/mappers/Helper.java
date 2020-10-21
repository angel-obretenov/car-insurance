package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.CarDto;
import com.safety.car.models.dto.rest.ModelDto;
import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.*;
import com.safety.car.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class Helper {

    private final GenericUtilityService<Address> addressService;
    private final UserService userService;
    private final CarService carService;

    @Autowired
    public Helper(GenericUtilityService<Address> addressService, UserService userService, CarService carService) {
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

    public static Car carDtoToCar(CarDto dto, ModelService modelService, BrandService brandService) {
        Car car = new Car();
        Model model = modelService.getById(dto.getModelId());
        Brand brand = brandService.getById(dto.getBrandId());

        car.setBrand(brand);
        car.setModel(model);
        car.setCubicCapacity(dto.getCubicCapacity());
        car.setRegistrationDate(dto.getRegistrationDate());
        car.setDriversAge(dto.getDriversAge());
        car.setHasAccidents(dto.isHasAccidents());
        car.setActive(dto.isActive());

        return car;
    }

    public static PolicyDetails dtoToPolicyDetails(PolicyDetailsDto dto,
                                                   UserService userService,
                                                   CarService carService,
                                                   GenericUtilityService<Address> addressService) {
        PolicyDetails policyDetails = new PolicyDetails();
        Car car = carService.getById(dto.getCarId());
        UserDetails user = userService.getById(dto.getUserDetailsId());
        Address address = addressService.getById(dto.getAddressId());

        policyDetails.setStartDate(dto.getStartDate());
        policyDetails.setEndDate(dto.getEndDate());
        policyDetails.setEmail(dto.getEmail());
//        addressRepository.createIfNotExist(userCreateDto.getAddressName());
//        Address address = addressRepository.findByName(userCreateDto.getAddressName());
        policyDetails.setPicture(dto.getPath());
        policyDetails.setPhoneNumber(dto.getPhoneNumber());
        policyDetails.setUser(user);
        policyDetails.setCar(car);

        return policyDetails;
    }

    public List<ModelDto> modelToDto(List<Model> models) {
        List<ModelDto> list = new ArrayList<>();
        for (Model model : models
        ) {
            ModelDto dto = new ModelDto();
            dto.setId(model.getId());
            dto.setYear(model.getYear());
            dto.setBrandId(model.getBrand().getId());
            dto.setName(model.getName());

            list.add(dto);
        }

        return list;
    }

    public UserCar toUserCar(Car car, UserDetails userDetails){
        UserCar userCar = new UserCar();
        userCar.setCarId(car);
        userCar.setUserId(userDetails);

        return userCar;
    }

}
