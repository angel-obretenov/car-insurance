package com.safety.car.repositories.interfaces;

public interface MulticriteriaRepository {

    Double getByCCAndAge(int cc, int age);
}
