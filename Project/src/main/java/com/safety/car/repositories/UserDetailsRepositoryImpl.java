package com.safety.car.repositories;

import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.UserDetailsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDetails getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE :id = id", UserDetails.class);
            query.setParameter("id", id);

            return query.list().get(0);
        }
    }

    @Override
    public List<UserDetails> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetails> query = session.createQuery("FROM UserDetails", UserDetails.class);

            return query.list();
        }
    }

    @Override
    public void create(UserDetails userDetails) {
        try (Session session = sessionFactory.openSession()) {

            session.save(userDetails);
        }
    }

    @Override
    public void update(UserDetails userDetails) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(userDetails);
            tx.commit();
        }
    }
}
