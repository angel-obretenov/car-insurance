package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.MulticriteriaTable;

import java.util.List;

public interface MulticriteriaRepository {

    Double getByCCAndAge(int cc, int age);

    List<MulticriteriaTable> getAll();

    void update(MulticriteriaTable multicriteriaTable);
}
