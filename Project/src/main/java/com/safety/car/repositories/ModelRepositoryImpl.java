package com.safety.car.repositories;

import com.safety.car.models.entity.Model;
import com.safety.car.repositories.interfaces.ModelRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModelRepositoryImpl implements ModelRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ModelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Model getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Model> query = session.createQuery("FROM Model WHERE id = :id", Model.class);
            query.setParameter("id", id);

            return query.list().get(0);
        }
    }

    @Override
    public List<Model> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Model> query = session.createQuery("FROM Model", Model.class);

            return query.list();
        }
    }

    @Override
    public List<Model> getByBrandId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Model> query = session.createQuery("FROM Model WHERE brand.id = :id", Model.class);
            query.setParameter("id", id);

            return query.list();
        }
    }
}
