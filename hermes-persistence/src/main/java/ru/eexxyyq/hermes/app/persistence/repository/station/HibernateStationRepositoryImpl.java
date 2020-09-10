package ru.eexxyyq.hermes.app.persistence.repository.station;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;
import ru.eexxyyq.hermes.app.persistence.hibernate.SessionFactoryBuilder;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author yatixonov
 * @created 10/09/2020 - 10:51
 * @project hermes
 */

public class HibernateStationRepositoryImpl implements StationRepository {
    private final SessionFactory sessionFactory;

    @Inject
    public HibernateStationRepositoryImpl(SessionFactoryBuilder sessionFactoryBuilder) {
        this.sessionFactory = sessionFactoryBuilder.getSessionFactory();
    }

    @Override
    public List<Station> findAllByCriteria(StationSearchCriteria stationCriteria) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Station> criteriaQuery = builder.createQuery(Station.class);
            Root<Station> root = criteriaQuery.from(Station.class);

            criteriaQuery.select(root);

            if (stationCriteria.getTransportType() != null) {
                criteriaQuery
                        .where(builder.equal(root
                                .get(Station.FIELD_TRANSPORT_TYPE), stationCriteria.getTransportType()));
            }

            if (!StringUtils.isEmpty(stationCriteria.getName())) {
                criteriaQuery
                        .where(builder.equal(root
                                .get(Station.FIELD_CITY).get(City.FIELD_NAME), stationCriteria.getName()));
            }

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
