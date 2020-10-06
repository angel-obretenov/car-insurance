package com.safety.car.services;

import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import com.safety.car.services.interfaces.MulticriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MulticriteriaServiceImpl implements MulticriteriaService {
    private final MulticriteriaRepository multicriteriaRepository;

    @Autowired
    public MulticriteriaServiceImpl(MulticriteriaRepository multicriteriaRepository) {
        this.multicriteriaRepository = multicriteriaRepository;
    }

    @Override
    public Double getByCCAndAge(int cc, int age) {
        return multicriteriaRepository.getByCCAndAge(cc, age);
    }
}