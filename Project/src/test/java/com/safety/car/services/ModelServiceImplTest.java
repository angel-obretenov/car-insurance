package com.safety.car.services;

import com.safety.car.models.entity.Brand;
import com.safety.car.models.entity.Model;
import com.safety.car.repositories.interfaces.ModelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelServiceImplTest {

    @Mock
    private ModelRepository mockRepo;

    @InjectMocks
    private ModelServiceImpl service;

    private final Brand brand = new Brand(1, "BMW");
    private final Model model = new Model(1, 2018, brand, "7 Series");

    @Test
    void getById() {
        when(mockRepo.getById(1))
                .thenReturn(model);

        Model result = service.getById(1);

        assertEquals(model, result);
    }

    @Test
    void getAll() {
        List<Model> list = new ArrayList<>();
        list.add(model);
        when(mockRepo.getAll())
                .thenReturn(list);

        List<Model> result = service.getAll();

        assertEquals(list, result);
    }

    @Test
    void getByBrandId() {
        List<Model> list = new ArrayList<>();
        list.add(model);
        when(mockRepo.getByBrandId(1))
                .thenReturn(list);

        List<Model> result = service.getByBrandId(1);

        assertEquals(list, result);
    }
}