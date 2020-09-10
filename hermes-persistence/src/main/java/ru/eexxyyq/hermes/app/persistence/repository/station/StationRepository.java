package ru.eexxyyq.hermes.app.persistence.repository.station;

import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;

import java.util.List;

/**
 * @author yatixonov
 * @created 10/09/2020 - 10:50
 * @project hermes
 */

public interface StationRepository {

    List<Station> findAllByCriteria(StationSearchCriteria stationCriteria);
}
