package com.safety.car.repositories;

import com.safety.car.models.entity.Brand;
import com.safety.car.repositories.interfaces.BrandRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl implements BrandRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public BrandRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Brand getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Brand> query = session.createQuery("FROM Brand WHERE id = :id", Brand.class);
            query.setParameter("id", id);

            return query.list().get(0);
        }
    }

    @Override
    public List<Brand> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Brand> query = session.createQuery("FROM Brand", Brand.class);

            return query.list();
        }
    }
}