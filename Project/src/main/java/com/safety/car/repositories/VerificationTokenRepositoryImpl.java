package com.safety.car.repositories;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.VerificationToken;
import com.safety.car.repositories.interfaces.VerificationTokenRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.TOKEN_NOT_VALID;

@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public VerificationTokenRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public VerificationToken findByVerificationToken(String verificationToken) {
        try (Session session = sessionFactory.openSession()) {
            Query<VerificationToken> query = session.createQuery("FROM VerificationToken WHERE :token = token", VerificationToken.class);
            query.setParameter("token", verificationToken);

            List<VerificationToken> list = query.list();

            if (list.isEmpty()) {
                throw new NotFoundException(TOKEN_NOT_VALID);
            }

            return list.get(0);
        }
    }

    @Override
    public void save(VerificationToken verificationToken) {
        try (Session session = sessionFactory.openSession()) {

            session.save(verificationToken);
        }
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.delete(verificationToken);
            tx.commit();
        }
    }
}