package com.safety.car.repositories;

import com.safety.car.models.entity.UserCar;
import com.safety.car.repositories.interfaces.UserCarRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserCarRepositoryImpl implements UserCarRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserCarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserCar getByUserId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserCar> query = session.createQuery("FROM UserCar WHERE :id = userId.id", UserCar.class);
            query.setParameter("id", id);

            return query.list().get(query.list().size() - 1);
        }
    }

    @Override
    public void create(UserCar userCar) {
        try (Session session = sessionFactory.openSession()) {

            session.save(userCar);
        }
    }

    @Override
    public void update(UserCar userCar) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.save(userCar);
            tx.commit();
        }
    }
}
