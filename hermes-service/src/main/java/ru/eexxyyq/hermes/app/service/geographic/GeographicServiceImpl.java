package ru.eexxyyq.hermes.app.service.geographic;

import ru.eexxyyq.hermes.app.common.utils.CommonUtils;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.RangeCriteria;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yatixonov
 * @created 03/09/2020 - 18:23
 * @project hermes
 */
public class GeographicServiceImpl implements GeographicService {
    private final List<City> cities;
    private long counter = 0L;

    public GeographicServiceImpl() {
        this.cities = new ArrayList<>();
    }

    @Override
    public List<City> findCities() {
        return CommonUtils.getSafeList(cities);
    }

    @Override
    public void saveCity(City city) {
        if(!this.cities.contains(city)) {
            city.setId(++counter);
            this.cities.add(city);
        }
    }

    @Override
    public Optional<City> findCityById(long id) {
        return this.cities.stream().filter(city -> city.getId().equals(id)).findFirst();
    }

    @Override
    public List<Station> searchStations(StationSearchCriteria criteria, RangeCriteria rangeCriteria) {
        Set<Station> stations = new HashSet<>();

        for (City city : this.cities) {
            stations.addAll(city.getStations());
        }

        return stations
                .stream()
                .filter(station -> station.match(criteria))
                .collect(Collectors.toList());
    }
}
