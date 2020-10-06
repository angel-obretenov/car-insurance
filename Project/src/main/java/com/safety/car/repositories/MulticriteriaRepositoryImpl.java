package com.safety.car.repositories;

import com.safety.car.models.entity.MulticriteriaTable;
import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MulticriteriaRepositoryImpl implements MulticriteriaRepository {
    private final SessionFactory sessionFactory;


    @Autowired
    public MulticriteriaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Double getByCCandAge(int cc, int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<MulticriteriaTable> query = session.createQuery("FROM MulticriteriaTable " +
                    "WHERE :cc BETWEEN ccMin AND ccMax AND :age BETWEEN carMinAge AND carMaxAge");
            query.setParameter("cc", cc);
            query.setParameter("age", age);

            return query.list().get(0).getBaseAmount();
        }
//        SELECT base_amount
//        FROM multicriteria_range
//        WHERE :cc BETWEEN cc_min AND cc_max AND :age  BETWEEN car_age_min AND car_age_max;
    }
}