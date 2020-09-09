package ru.eexxyyq.hermes.app.service.geographic;

import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.RangeCriteria;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;
import ru.eexxyyq.hermes.app.persistence.repository.CityRepository;
import ru.eexxyyq.hermes.app.persistence.repository.InMemoryCityRepositoryImpl;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yatixonov
 * @created 03/09/2020 - 18:23
 * @project hermes
 */
public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cities;

    @Inject
    public GeographicServiceImpl(CityRepository cities) {
        this.cities = cities;
    }

    @Override
    public List<City> findCities() {
        return cities.findAll();
    }

    @Override
    public void saveCity(City city) {
        cities.save(city);
    }

    @Override
    public Optional<City> findCityById(long id) {
        return Optional.ofNullable(cities.findById(id));
    }

    @Override
    public List<Station> searchStations(StationSearchCriteria criteria, RangeCriteria rangeCriteria) {
        Set<Station> stations = new HashSet<>();

        cities.findAll().forEach(city -> stations.addAll(city.getStations()));

        return stations
                .stream()
                .filter(station -> station.match(criteria))
                .collect(Collectors.toList());
    }
}
