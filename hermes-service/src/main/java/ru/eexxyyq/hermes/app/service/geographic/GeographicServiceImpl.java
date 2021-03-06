package ru.eexxyyq.hermes.app.service.geographic;

import ru.eexxyyq.hermes.app.common.exception.ValidationException;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.search.criteria.RangeCriteria;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;
import ru.eexxyyq.hermes.app.persistence.repository.city.CityRepository;
import ru.eexxyyq.hermes.app.persistence.repository.station.StationRepository;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author yatixonov
 * @created 03/09/2020 - 18:23
 * @project hermes
 */
public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cities;
    private final StationRepository stations;
    private final Validator validator;

    @Inject
    public GeographicServiceImpl(CityRepository cities, StationRepository stations) {
        this.cities = cities;
        this.stations = stations;
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<City> findCities() {
        return cities.findAll();
    }

    @Override
    public void saveCity(City city) {
        Set<ConstraintViolation<City>> constraintViolations = validator.validate(city);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationException("City validation failure", constraintViolations);
        }
        cities.save(city);
    }

    @Override
    public Optional<City> findCityById(long id) {
        return Optional.ofNullable(cities.findById(id));
    }

    @Override
    public List<Station> searchStations(StationSearchCriteria criteria, RangeCriteria rangeCriteria) {
        return stations.findAllByCriteria(criteria);
    }
}
