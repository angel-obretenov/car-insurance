package com.safety.car.repositories;

import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class MulticriteriaRepositoryImpl implements MulticriteriaRepository {
    private final SessionFactory sessionFactory;


    @Autowired
    public MulticriteriaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Double getByCCAndAge(int cc, int age) {
        try (Session session = sessionFactory.openSession()) {

            String sql = format("SELECT baseAmount FROM MulticriteriaTable " +
                    "WHERE %d BETWEEN ccMin AND ccMax AND %d BETWEEN carMinAge AND carMaxAge", cc, age);

            Query<Double> query = session.createQuery(sql, Double.class);

            return query.list().get(0);
        }
//        SELECT base_amount
//        FROM multicriteria_range
//        WHERE :cc BETWEEN cc_min AND cc_max AND :age  BETWEEN car_age_min AND car_age_max;
    }
}