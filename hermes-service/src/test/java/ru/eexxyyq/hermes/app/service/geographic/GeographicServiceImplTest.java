package ru.eexxyyq.hermes.app.service.geographic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;
import ru.eexxyyq.hermes.app.persistence.repository.InMemoryCityRepositoryImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 04/09/2020 - 16:52
 * @project hermes
 */

class GeographicServiceImplTest {
    GeographicService service;
    private final long DEFAULT_CITY_ID = 1L;

    @BeforeEach
    void setUp() {
        service = new GeographicServiceImpl(new InMemoryCityRepositoryImpl());
    }

    @Test
    void checkCitiesDataForEmpty() {
        List<City> cities = service.findCities();
        assertTrue(cities.isEmpty());
    }
    @Test
    void whenTryToSaveValidCityThenSuccess() {
        City city = new City("Moscow");
        service.saveCity(city);
        List<City> cities = service.findCities();
        assertEquals(1, cities.size());
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testFindCityByIdSuccess() {
        City city = new City("Moscow");
        service.saveCity(city);
        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID);
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
    }

    @Test
    void testFindCityByIdFail() {
        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID);
        assertFalse(foundCity.isPresent());
    }

    @Test
    void testFindStationsSuccess() {
        City city = new City("Moscow");
        city.addStation(TransportType.AVIA);
        service.saveCity(city);
        StationSearchCriteria criteria = new StationSearchCriteria();
        criteria.setName("Moscow");
        criteria.setTransportType(TransportType.AVIA);
        List<Station> stations = service.searchStations(criteria, null);
        assertEquals(1, stations.size());
    }

    @Test
    void testFindStationsFail() {
        StationSearchCriteria criteria = new StationSearchCriteria();
        criteria.setName("Moscow");
        criteria.setTransportType(TransportType.AVIA);
        List<Station> stations = service.searchStations(criteria, null);
        assertEquals(0, stations.size());
    }


}