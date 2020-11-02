package com.safety.car.services;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Brand;
import com.safety.car.repositories.interfaces.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    BrandRepository mockRepo;

    @InjectMocks
    BrandServiceImpl service;

    Brand brand = new Brand();

    {
        brand.setId(1);
        brand.setName("BMW");
    }

    @Test
    void getAll_ShouldReturn_AllList() {
        List<Brand> list = new ArrayList<>();
        list.add(brand);
        when(mockRepo.getAll()).thenReturn(list);

        List<Brand> results = service.getAll();

        assertEquals(list, results);
    }

    @Test
    void getById_ShouldReturn_WhenInvoked() {
        when(mockRepo.getById(1)).thenReturn(brand);

        Brand test = service.getById(1);

        assertEquals(brand, test);
    }
}













