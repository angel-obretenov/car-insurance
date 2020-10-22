package com.safety.car.repositories;

import com.safety.car.exceptions.EmptyException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.repositories.interfaces.PolicyDetailsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.POLICY_EMPTY_ERROR;
import static com.safety.car.utils.constants.Constants.POLICY_ID_ERROR;
import static java.lang.String.format;

@Repository
public class PolicyDetailsRepositoryImpl implements PolicyDetailsRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PolicyDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PolicyDetails> getAll() {
        try (Session session = sessionFactory.openSession()){
            Query<PolicyDetails> query = session.createQuery("FROM PolicyDetails ", PolicyDetails.class);
            List<PolicyDetails> result = query.list();

            if (result.isEmpty()) throw new EmptyException(POLICY_EMPTY_ERROR);

            return result;
        }
    }

    @Override
    public PolicyDetails getById(int id) {
        try (Session session = sessionFactory.openSession()){
            Query<PolicyDetails> query = session.createQuery("FROM PolicyDetails " +
                    "WHERE :id = id", PolicyDetails.class);
            query.setParameter("id", id);
            List<PolicyDetails> result = query.list();

            if (result.isEmpty()) throw new NotFoundException(format(POLICY_ID_ERROR, id));

            return result.get(0);
        }
    }

    @Override
    public void create(PolicyDetails policyDetails) {
        try (Session session = sessionFactory.openSession()){

            session.save(policyDetails);
        }
    }

    @Override
    public void update(PolicyDetails policyDetails) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();

            session.update(policyDetails);
            tx.commit();
        }
    }
}
