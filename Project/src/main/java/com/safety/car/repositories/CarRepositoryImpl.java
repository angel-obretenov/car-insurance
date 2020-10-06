package com.safety.car.repositories;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Car;
import com.safety.car.repositories.interfaces.CarRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.String.format;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Car getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Car> query = session.createQuery("SELECT c FROM Car c " +
                    "WHERE :id = c.id", Car.class);
            query.setParameter("id", id);

            if (query.list().size() == 0) throw new NotFoundException(format("Car with id %d not found", id));


            return query.list().get(0);
        }
    }

    @Override
    public List<Car> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Car> query = session.createQuery("FROM Car", Car.class);

            return query.list();
        }
    }

    @Override
    public void create(Car car) {
        try (Session session = sessionFactory.openSession()) {

            session.save(car);
        }
    }

    @Override
    public void update(Car car) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(car);
            tx.commit();
        }
    }

    @Override
    public int simulateOffer(Car car) {
        try (Session session = sessionFactory.openSession()) {

            session.save(car);
            Query<Car> query = session.createQuery("FROM Car ORDER BY id DESC", Car.class);
            query.setMaxResults(1);

            return query.list().get(0).getId();
        }
    }
}
