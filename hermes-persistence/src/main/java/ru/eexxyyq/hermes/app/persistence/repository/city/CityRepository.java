package ru.eexxyyq.hermes.app.persistence.repository.city;

import ru.eexxyyq.hermes.app.model.entity.geography.City;

import java.util.List;

/**
 * @author yatixonov
 * @created 09/09/2020 - 15:53
 * @project hermes
 */

public interface CityRepository {
    void save (City city);
    City findById(Long id);
    void delete(Long id);
    List<City> findAll();
}
