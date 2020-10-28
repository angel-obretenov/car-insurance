package com.safety.car.repositories;

import com.safety.car.models.entity.UserDetails;
import com.safety.car.repositories.interfaces.UserDetailsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.safety.car.utils.constants.Constants.USER_NOT_FOUND_EMAIL;
import static com.safety.car.utils.constants.Constants.USER_NOT_FOUND_ID;
import static java.lang.String.format;

@Repository
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserDetails> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetails> query = session.createQuery("FROM UserDetails", UserDetails.class);

            return query.list();
        }
    }

    @Override
    public UserDetails getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE :id = id", UserDetails.class);
            query.setParameter("id", id);

            if (query.list().isEmpty()) {
                throw new EntityNotFoundException(
                        format(USER_NOT_FOUND_ID, id));
            }

            return query.list().get(0);
        }
    }

    @Override
    public UserDetails getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE :email = email", UserDetails.class);
            query.setParameter("email", email);

            if (query.list().isEmpty()) {
                throw new EntityNotFoundException(
                        format(USER_NOT_FOUND_EMAIL, email));
            }

            return query.list().get(0);
        }
    }

    @Override
    public void create(UserDetails userDetails) {
        try (Session session = sessionFactory.openSession()) {

            userDetails.setEnabled(false);
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

    @Override
    public boolean emailExists(String email) {
        return getAll()
                .stream()
                .anyMatch(e -> e.getEmail().equalsIgnoreCase(email));
    }
}