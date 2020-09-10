package ru.eexxyyq.hermes.app.service.geographic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;
import ru.eexxyyq.hermes.app.persistence.hibernate.SessionFactoryBuilder;
import ru.eexxyyq.hermes.app.persistence.repository.city.CityRepository;
import ru.eexxyyq.hermes.app.persistence.repository.city.HibernateCityRepository;
import ru.eexxyyq.hermes.app.persistence.repository.station.HibernateStationRepositoryImpl;
import ru.eexxyyq.hermes.app.persistence.repository.station.StationRepository;

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
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        CityRepository repository = new HibernateCityRepository(builder);
        StationRepository stationRepository = new HibernateStationRepositoryImpl(builder);
        service = new GeographicServiceImpl(repository, stationRepository);
    }

    @Test
    void whenTryToSaveValidCityThenSuccess() {
        City city = new City("Moscow");
        city.setRegion("Msc");
        city.setDistrict("Msc");
        service.saveCity(city);
        List<City> cities = service.findCities();
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testFindCityByIdSuccess() {
        City city = new City("Moscow");
        city.setRegion("Msc");
        city.setDistrict("Msc");
        service.saveCity(city);
        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID);
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
    }

    @Test
    void testFindCityByIdFail() {
        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID + 10000000L);
        assertFalse(foundCity.isPresent());
    }

    @Test
    void testFindStationsSuccess() {
        City city = new City("Moscow");
        city.setRegion("Msc");
        city.setDistrict("Msc");
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
        criteria.setName("122");
        criteria.setTransportType(TransportType.AVIA);
        List<Station> stations = service.searchStations(criteria, null);
        assertEquals(0, stations.size());
    }


}