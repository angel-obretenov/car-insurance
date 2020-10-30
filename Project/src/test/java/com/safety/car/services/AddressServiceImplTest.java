package com.safety.car.services;

import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository mockRepo;

    @InjectMocks
    AddressServiceImpl service;

    Address address = new Address(2, "Ulitza Vtora");

    @Test
    void getAll() {
        //Arrange
        Address address1 = new Address(1, "Ulitza Parva");
//        Address address2 = new Address(2, "Ulitza Vtora");
        Address address3 = new Address(3, "Ulitza Treta");
        List<Address> threeAddresses = new ArrayList<>();
        threeAddresses.add(address1);
        threeAddresses.add(address);
        threeAddresses.add(address3);

        when(mockRepo.getAll())
                .thenReturn(threeAddresses);
        // Act
        List<Address> resultAddresses = service.getAll();

        // Assert
        Assertions.assertEquals(threeAddresses, resultAddresses);
    }

    //Arrange
    // Act
    // Assert

    @Test
    void getById() {
        //Arrange
        when(mockRepo.getById(2))
                .thenReturn(address);

        // Act
        Address result = service.getById(2);

        // Assert
        Assertions.assertEquals(2, result.getId());
        Assertions.assertEquals("Ulitza Vtora", result.getAddress());
    }

    @Test
    void createIfNotExist() {
        //Arrange

        //TODO ne znam kak moje da stane
//        do().when(mockRepo).createAddress(any(Address.class));
//        when(Address.class).
//        // Act
//        service.createIfNotExist("Test");
//
//        // Assert
//        assertFalse(service.isNotSaved("Test"));
    }

    @Test
    void isNotSaved() {
        //Arrange
        List<Address> list = new ArrayList<>();
        list.add(address);

        when(mockRepo.getAll()).thenReturn(list);
        // Act

        // Assert
        Assertions.assertTrue(service.isNotSaved("Nesushtestvuvasht adres"));
        Assertions.assertFalse(service.isNotSaved(address.getAddress()));
    }

    @Test
    void findByName() {
        //Arrange
        when(mockRepo.findByName("Ulitza Vtora"))
                .thenReturn(address);

        // Act
        Address result = service.findByName("Ulitza Vtora");

        // Assert
        Assertions.assertEquals("Ulitza Vtora", result.getAddress());
        Assertions.assertEquals(2, result.getId());
    }


}