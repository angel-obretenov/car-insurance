package com.safety.car.services;

import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MulticriteriaServiceImplTest {

    @InjectMocks
    private MulticriteriaServiceImpl multiService;

    @Mock
    private MulticriteriaRepository multiMockRepository;


    @Test
    public void getById_ShouldReturn_WhenEntityExists() {
        //arrange
        Double number = 2.2;
        Mockito.when(multiMockRepository.getByCCAndAge(2, 19))
                .thenReturn(number);
        //act
        Double result = multiService.getByCCAndAge(2, 19);
        //assert
        Assertions.assertEquals(result, number);
    }
}