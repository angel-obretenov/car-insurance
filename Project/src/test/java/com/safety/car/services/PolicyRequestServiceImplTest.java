package com.safety.car.services;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.repositories.interfaces.PolicyRequestRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PolicyRequestServiceImplTest {

    @InjectMocks
    private PolicyRequestServiceImpl policyService;

    @Mock
    private PolicyRequestRepository policyMockRepository;

    private PolicyRequest policyTest;
    private List<PolicyRequest> mockList;

    @BeforeEach
    void initialization() {
        policyTest = new PolicyRequest(1);
        mockList = Collections.singletonList(policyTest);
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
        PolicyRequest result = policyService.getById(1);
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
    public void getAll_ShouldReturn_List() {
        //arrange
        Mockito.when(policyMockRepository.getAll()).thenReturn(mockList);
        // act
        List<PolicyRequest> getListFromService = policyService.getAll();
        //assert
        assertEquals(mockList, getListFromService);
    }

    @Test
    public void getAllPending_ShouldReturn_List() {
        //arrange
        Mockito.when(policyMockRepository.getAllPending()).thenReturn(mockList);
        // act
        List<PolicyRequest> getListFromService = policyService.getAllPending();
        //assert
        assertEquals(mockList, getListFromService);
    }

    @Test
    public void getUserPolicies_ShouldReturn_List() {
        //arrange
        Mockito.when(policyMockRepository.getUserPolicies(Mockito.anyInt())).thenReturn(mockList);
        // act
        List<PolicyRequest> getListFromService = policyService.getUserPolicies(Mockito.anyInt());
        //assert
        assertEquals(mockList, getListFromService);
    }

    @Test
    public void search_ShouldReturn_List() {
        //arrange
        Mockito.when(policyMockRepository.search(Optional.of(1), Optional.empty())).thenReturn(mockList);
        // act
        List<PolicyRequest> getListFromService = policyService.search(Optional.of(1), Optional.empty());
        //assert
        assertEquals(mockList, getListFromService);
    }
}