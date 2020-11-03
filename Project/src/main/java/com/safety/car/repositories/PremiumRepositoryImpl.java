package com.safety.car.repositories;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.PremiumRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.PREMIUM_ERROR;
import static com.safety.car.utils.constants.Constants.PREMIUM_ID_ERROR;
import static java.lang.String.format;

@Repository
public class PremiumRepositoryImpl implements PremiumRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PremiumRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PremiumValues getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<PremiumValues> query = session.createQuery("FROM PremiumValues WHERE id = :id", PremiumValues.class);
            query.setParameter("id", id);

            List<PremiumValues> premium = query.list();

            if (premium.size() == 0) {
                throw new NotFoundException(format(PREMIUM_ID_ERROR, id));
            }

            return premium.get(0);
        }
    }

    @Override
    public List<PremiumValues> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<PremiumValues> query = session.createQuery("FROM PremiumValues", PremiumValues.class);

            List<PremiumValues> premium = query.list();

            if (premium.size() == 0) {
                throw new NotFoundException(PREMIUM_ERROR);
            }

            return premium;
        }
    }

    @Override
    public void create(PremiumValues premiumValues) {
        try (Session session = sessionFactory.openSession()) {

            session.save(premiumValues);
        }
    }

    @Override
    public void update(PremiumValues premiumValues) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(premiumValues);
            tx.commit();
        }
    }
}