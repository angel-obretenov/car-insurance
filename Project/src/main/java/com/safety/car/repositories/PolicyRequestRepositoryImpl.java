package com.safety.car.repositories;

import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.repositories.interfaces.PolicyRequestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.lang.String.format;

@Repository
public class PolicyRequestRepositoryImpl implements PolicyRequestRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PolicyRequestRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PolicyRequest> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<PolicyRequest> query = session.createQuery("FROM PolicyRequest ", PolicyRequest.class);

            return query.list();
        }
    }

    @Override
    public PolicyRequest getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<PolicyRequest> query = session.createQuery("FROM PolicyRequest WHERE :id = id", PolicyRequest.class);
            query.setParameter("id", id);

            if (query.list().isEmpty()) {
                throw new EntityNotFoundException(
                        format("Policy with id: %d, was not found!", id));
            }

            return query.list().get(0);
        }
    }

    @Override
    public void create(PolicyRequest policyRequest) {
        try (Session session = sessionFactory.openSession()) {
            session.save(policyRequest);
        }
    }

    @Override
    public void update(PolicyRequest policyToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(policyToUpdate);
            tx.commit();
        }
    }
}