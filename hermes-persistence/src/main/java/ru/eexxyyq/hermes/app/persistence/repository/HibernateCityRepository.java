package ru.eexxyyq.hermes.app.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.persistence.hibernate.SessionFactoryBuilder;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author yatixonov
 * @created 09/09/2020 - 22:28
 * @project hermes
 */

public class HibernateCityRepository implements CityRepository {
    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder builder) {
        this.sessionFactory = builder.getSessionFactory();
    }

    @Override
    public void save(City city) {
        try (Session session = sessionFactory.openSession()) {
            city.prePersist();
            if (city.getStations() != null) {
                city.getStations().forEach(BaseEntity::prePersist);
            }
            session.saveOrUpdate(city);
        }
    }

    @Override
    public City findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            City cityToDelete = session.get(City.class, id);
            session.delete(cityToDelete);
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<City> cq = cb.createQuery(City.class);
            Root<City> rootEntry = cq.from(City.class);
            CriteriaQuery<City> all = cq.select(rootEntry);

            TypedQuery<City> allQuery = session.createQuery(all);
            return allQuery.getResultList();

        }
    }
}
