package ru.eexxyyq.hermes.app.service.geographic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 04/09/2020 - 16:52
 * @project hermes
 */

class GeographicServiceImplTest {
    private static GeographicService service;
    private static ExecutorService executorService;
    private final long DEFAULT_CITY_ID = 1L;

    @BeforeAll
    public static void setUp() {
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        CityRepository repository = new HibernateCityRepository(builder);
        StationRepository stationRepository = new HibernateStationRepositoryImpl(builder);
        service = new GeographicServiceImpl(repository, stationRepository);

        executorService = Executors.newCachedThreadPool();
    }

    @AfterAll
    public static void tearDown() {
        executorService.shutdown();
    }

    @Test
    void whenTryToSaveValidCityThenSuccess() {
        City city = new City("Moscow");
        city.setRegion("Msc");
        city.setDistrict("Msc");
        service.saveCity(city);
        List<City> cities = service.findCities();
        assertEquals("Odessa0", cities.get(0).getName());
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

    @Test
    void testSaveMultipleCitiesSuccess() {
        int cityCount = service.findCities().size();
        int addedCount = 100_000;
        for (int i = 0; i < addedCount; i++) {
            City city = new City("Odessa" + i);
            city.setDistrict("Odessa");
            city.setRegion("Odessa");
            city.addStation(TransportType.AUTO);
            service.saveCity(city);
        }

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + addedCount);
    }

    @Test
    void testSaveMultipleCitiesConcurrentlySuccess() {
        int cityCount = service.findCities().size();
        int threadCount = 200;
        int batchCount = 10;
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                for (int j = 0; j < batchCount; j++) {
                    City city = new City("Lviv_" + Math.random());
                    city.setDistrict("Lviv");
                    city.setRegion("Lviv");
                    city.addStation(TransportType.AUTO);
                    service.saveCity(city);
                }
            }));
        }

        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + threadCount * batchCount);
    }


    @Test
    void testSaveOneCityConcurrentlySuccess() {
        City city = new City("Nikolaev");
        city.setDistrict("Nikolaev");
        city.setRegion("Nikolaev");
        city.addStation(TransportType.AUTO);
        service.saveCity(city);

        int cityCount = service.findCities().size();
        int threadCount = 200;
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                city.setName("Nikolaev" + Math.random());
                city.setDistrict("Msc");
                city.setRegion("Msc");
                service.saveCity(city);
            }));
        }
        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount);
    }

    private void waitForFutures(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }
}