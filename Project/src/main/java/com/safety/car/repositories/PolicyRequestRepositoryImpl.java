package com.safety.car.repositories;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.repositories.interfaces.PolicyRequestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static com.safety.car.utils.constants.Constants.CRITERIA_ERROR;
import static com.safety.car.utils.constants.Constants.POLICY_ID_NOT_FOUND;
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
    public List<PolicyRequest> getAllPending() {
        try (Session session = sessionFactory.openSession()) {
            Query<PolicyRequest> query = session.createQuery("FROM PolicyRequest WHERE isApproved is null", PolicyRequest.class);

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
                        format(POLICY_ID_NOT_FOUND, id));
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

    public List<PolicyRequest> search(Optional<Integer> id, Optional<Integer> isApproved) {
        try (Session session = sessionFactory.openSession()) {
            Set<PolicyRequest> list = new HashSet<>();

            if (id.isPresent()) {
                Query<PolicyRequest> query = session.createQuery("FROM PolicyRequest WHERE id = :id");
                query.setParameter("id", id.get());

                list.addAll(query.list());
            }

            if (isApproved.isPresent()) {
                Query<PolicyRequest> query = null;
                switch (isApproved.get()) {
                    case -1:
                        break;
                    case 0:
                        query = session.createQuery("FROM PolicyRequest WHERE isApproved = false ");
                        list.addAll(query.list());
                        break;
                    case 1:
                        query = session.createQuery("FROM PolicyRequest WHERE isApproved = true");
                        list.addAll(query.list());
                        break;
                    case 2:
                        query = session.createQuery("FROM PolicyRequest WHERE isApproved = null");
                        list.addAll(query.list());
                        break;
                }
            }

            if (list.size() == 0) {
                //isApproved cant be empty, so .get will always work
                if (id.isEmpty() && isApproved.get() == -1) {
                    return getAll();
                } else {
                    throw new NotFoundException(CRITERIA_ERROR);
                }
            } else {
                return new ArrayList<>(list);
            }
        }
    }
}