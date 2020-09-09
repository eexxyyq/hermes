package ru.eexxyyq.hermes.app.persistence.repository;

import ru.eexxyyq.hermes.app.common.utils.CommonUtils;
import ru.eexxyyq.hermes.app.model.entity.geography.City;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yatixonov
 * @created 09/09/2020 - 15:56
 * @project hermes
 */

public class InMemoryCityRepositoryImpl implements CityRepository {
    private final List<City> cities;
    private long count = 0L;
    private long stationCount = 0L;

    public InMemoryCityRepositoryImpl() {
        cities = new ArrayList<>();
    }

    @Override
    public void save(City city) {
        if (!cities.contains(city)) {
            city.setId(++count);
            cities.add(city);
        }

        city.getStations().forEach(station -> {
            if (station.getId() != null && station.getId().equals(0L)) {
                station.setId(++stationCount);
            }
        });
    }

    @Override
    public City findById(Long id) {
        return cities.stream().filter(city -> city.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        City cityToDelete = findById(id);
        if (cityToDelete != null) {
            cities.remove(cityToDelete);
        }
    }

    @Override
    public List<City> findAll() {
        return CommonUtils.getSafeList(cities);
    }
}
