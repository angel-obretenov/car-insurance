package com.safety.car.services.interfaces;

import com.safety.car.models.entity.MulticriteriaTable;

import java.util.List;

public interface MulticriteriaService {

    Double getByCCAndAge(int cc, int age);
}
