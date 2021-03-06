package com.safety.car.repositories;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.ADDRESS_ID_NOT_FOUND;
import static com.safety.car.utils.constants.Constants.ADDRESS_NOT_FOUND;
import static java.lang.String.format;

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
    public Address findByName(String addressName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Address> query = session.createQuery("From Address where address = :addressName", Address.class);
            query.setParameter("addressName", addressName);

            List<Address> addresses = query.list();

            if (addresses.isEmpty()) {
                throw new NotFoundException(
                        format(ADDRESS_NOT_FOUND, addressName));
            }

            return addresses.get(0);
        }
    }

    @Override
    public Address getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Address> query = session.createQuery("From Address where :id = id", Address.class);
            query.setParameter("id", id);

            List<Address> addresses = query.list();

            if (addresses.isEmpty()) {
                throw new NotFoundException(
                        format(ADDRESS_ID_NOT_FOUND, id));
            }

            return addresses.get(0);
        }
    }

    @Override
    public boolean isNotSaved(String addressName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Address> query = session.createQuery("From Address where address = :addressName", Address.class);
            query.setParameter("addressName", addressName);

            return query.list().isEmpty();
        }
    }

    @Override
    public void createAddress(Address address) {
        try (Session session = sessionFactory.openSession()) {
            session.save(address);
        }
    }
}