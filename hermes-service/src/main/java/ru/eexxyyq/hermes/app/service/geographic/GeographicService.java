package ru.eexxyyq.hermes.app.service.geographic;

import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.RangeCriteria;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;

import java.util.List;
import java.util.Optional;

/**
 * @author yatixonov
 * @created 03/09/2020 - 18:23
 * @project hermes
 */

public interface GeographicService {
    List<City> findCities();

    void saveCity(City city);

    Optional<City> findCityById(long id);

    List<Station> searchStations(StationSearchCriteria criteria, RangeCriteria rangeCriteria);
}
