package com.safety.car.services;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.repositories.interfaces.PolicyDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PolicyDetailsServiceImplTest {


    @InjectMocks
    private PolicyDetailsServiceImpl policyService;

    @Mock
    private PolicyDetailsRepository policyMockRepository;

    private PolicyDetails policyTest;

    @BeforeEach
    void initialization() {
        policyTest = new PolicyDetails(1, "test@email.com");
    }

    @Test
    public void create_ShouldCreate() {
        //arrange, act
        policyService.create(policyTest);
        //assert
        Mockito.verify(policyMockRepository, Mockito.times(1)).create(policyTest);
    }

    @Test
    public void update_ShouldCreate() {
        //arrange, act
        policyService.update(policyTest);
        //assert
        Mockito.verify(policyMockRepository, Mockito.times(1)).update(policyTest);
    }

    @Test
    public void getById_ShouldReturn_WhenEntityExists() {
        //arrange
        Mockito.when(policyMockRepository.getById(1))
                .thenReturn(policyTest);
        //act
        PolicyDetails result = policyService.getById(1);
        //assert
        Assertions.assertEquals(result, policyTest);
    }

    @Test
    public void getById_ShouldThrow_WhenEntityDoesNotExist() {
        //arrange
        Mockito.when(policyMockRepository.getById(2))
                .thenThrow(new NotFoundException("not found"));
        //act, assert
        Assertions.assertThrows(NotFoundException.class,
                () -> policyService.getById(2));
    }

    @Test
    public void delete_ShouldDelete() {
        //arrange, act
        policyService.delete(policyTest);
        //assert
        Mockito.verify(policyMockRepository, Mockito.times(1)).delete(policyTest);
    }

    @Test
    public void getAll_ShouldReturn_List() {
        //arrange
        List<PolicyDetails> mockList = new ArrayList<>();
        mockList.add(policyTest);
        Mockito.when(policyMockRepository.getAll()).thenReturn(mockList);
        // act
        List<PolicyDetails> getListFromService = policyService.getAll();
        //assert
        assertEquals(mockList, getListFromService);
    }
}