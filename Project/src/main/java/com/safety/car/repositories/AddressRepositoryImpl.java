package com.safety.car.repositories;

import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public AddressRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Address> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Address> query = session.createQuery("From Address", Address.class);

            return query.list();
        }
    }

    @Override
    public void createAddress(Address address) {
        try (Session session = sessionFactory.openSession()) {

            session.save(address);
        }
    }

    @Override
    public void updateAddress(Address address) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(address);
            tx.commit();
        }
    }
}