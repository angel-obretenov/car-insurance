package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.VerificationToken;


public interface VerificationTokenRepository{

    VerificationToken findByVerificationToken(String verificationToken);

    void save(VerificationToken verificationToken);

    void delete(VerificationToken verificationToken);

}
