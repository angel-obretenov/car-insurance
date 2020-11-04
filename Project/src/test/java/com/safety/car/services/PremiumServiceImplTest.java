package com.safety.car.services;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.PremiumRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PremiumServiceImplTest {

    @InjectMocks
    private PremiumServiceImpl premiumService;

    @Mock
    private PremiumRepository premiumMockRepository;

    private PremiumValues premiumTest;
    private List<PremiumValues> mockList;

    @BeforeEach
    void initialization() {
        premiumTest = new PremiumValues();
        mockList = Collections.singletonList(premiumTest);
    }

    @Test
    public void create_ShouldCreate() {
        //arrange, act
        premiumService.create(premiumTest);
        //assert
        Mockito.verify(premiumMockRepository, Mockito.times(1)).create(premiumTest);
    }

    @Test
    public void update_ShouldCreate() {
        //arrange, act
        premiumService.update(premiumTest);
        //assert
        Mockito.verify(premiumMockRepository, Mockito.times(1)).update(premiumTest);
    }

    @Test
    public void getById_ShouldReturn_WhenEntityExists() {
        //arrange
        Mockito.when(premiumMockRepository.getById(1))
                .thenReturn(premiumTest);
        //act
        PremiumValues result = premiumService.getById(1);
        //assert
        Assertions.assertEquals(result, premiumTest);
    }

    @Test
    public void getById_ShouldThrow_WhenEntityDoesNotExist() {
        //arrange
        Mockito.when(premiumMockRepository.getById(2))
                .thenThrow(new NotFoundException("not found"));
        //act, assert
        Assertions.assertThrows(NotFoundException.class,
                () -> premiumService.getById(2));
    }

    @Test
    public void getAll_ShouldReturn_List() {
        //arrange
        Mockito.when(premiumMockRepository.getAll()).thenReturn(mockList);
        // act
        List<PremiumValues> getListFromService = premiumService.getAll();
        //assert
        assertEquals(mockList, getListFromService);
    }
}