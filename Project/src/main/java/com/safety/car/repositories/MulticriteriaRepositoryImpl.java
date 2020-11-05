package com.safety.car.repositories;

import com.safety.car.exceptions.EmptyException;
import com.safety.car.models.entity.MulticriteriaTable;
import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.safety.car.utils.constants.Constants.CRITERIA_TABLE_EMPTY_ERROR;
import static java.lang.String.format;

@Repository
public class MulticriteriaRepositoryImpl implements MulticriteriaRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public MulticriteriaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<MulticriteriaTable> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<MulticriteriaTable> query = session.createQuery("FROM MulticriteriaTable "
                    , MulticriteriaTable.class);

            return query.list();
        }
    }

    @Override
    public Double getByCCAndAge(int cc, int age) {
        try (Session session = sessionFactory.openSession()) {

            String sql = format("SELECT baseAmount FROM MulticriteriaTable " +
                    "WHERE %d BETWEEN ccMin AND ccMax AND %d BETWEEN carMinAge AND carMaxAge", cc, age);
            Query<Double> query = session.createQuery(sql, Double.class);

            List<Double> list = query.list();

            if (list.isEmpty()) {
                throw new EmptyException(CRITERIA_TABLE_EMPTY_ERROR);
            }

            return list.get(0);
        }
    }

    public void update(MulticriteriaTable multicriteriaTable) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.update(multicriteriaTable);
            tx.commit();
        }
    }

}