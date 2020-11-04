package com.safety.car.services;

import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository mockRepo;

    @InjectMocks
    private AddressServiceImpl service;

    private final Address address = new Address(2, "Ulitza Vtora");

    @Test
    void getAll() {
        //Arrange
        List<Address> threeAddresses = new ArrayList<>();
        threeAddresses.add(address);
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

//    @Test
//    void createIfNotExist() {
//        //Arrange
//        when(mockRepo.isNotSaved(address.getAddress()))
//                .thenReturn(true);
//
//        // Act, Assert
//        verify(mockRepo, times(1))
//                .createAddress(address);
//    }

    @Test
    void isNotSaved() {
        //Arrange
        when(mockRepo.isNotSaved(address.getAddress())).thenReturn(false);
        // Act  Assert
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