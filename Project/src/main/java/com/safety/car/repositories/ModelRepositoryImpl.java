package com.safety.car.repositories;

import com.safety.car.exceptions.EmptyException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Model;
import com.safety.car.repositories.interfaces.ModelRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.*;
import static java.lang.String.format;

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

            List<Model> model = query.list();
            if (model.isEmpty()) throw new NotFoundException(format(MODEL_ID_ERROR, id));

            return model.get(0);
        }
    }

    @Override
    public List<Model> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Model> query = session.createQuery("FROM Model", Model.class);

            List<Model> model = query.list();
            if (model.isEmpty()) throw new EmptyException(MODEL_EMPTY_ERROR);

            return model;
        }
    }

    @Override
    public List<Model> getByBrandId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Model> query = session.createQuery("FROM Model WHERE brand.id = :id ORDER BY name", Model.class);
            query.setParameter("id", id);

            List<Model> model = query.list();
            if (model.isEmpty()) throw new NotFoundException(format(MODEL_BRAND_ID_ERROR, id));

            return model;
        }
    }
}
