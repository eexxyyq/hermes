package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 03/09/2020 - 16:00
 * @project hermes
 */

class CityTest {
    private City city;

    @BeforeEach
    void setUp() {
        city = new City("Moscow");
    }

    @Test
    public void whenAddValidStationThenSuccess() {
        Station station = new Station(city, TransportType.AUTO);
        city.addStation(station);
        assertTrue(containsStation(city, station));
        assertEquals(city, station.getCity());
    }

    @Test
    public void whenAddValidStationByTypeThenSuccess() {
        city.addStation(TransportType.AVIA);
        assertEquals(city.getStations().size(), 1);
    }

    @Test
    public void whenTryToAddNullStationThenException() {
        assertThrows(NullPointerException.class, () -> city.addStation((Station) null));
    }

    @Test
    public void whenTryToAddStationByNullTypeThenException() {
        assertThrows(NullPointerException.class, () -> city.addStation((TransportType) null));
    }

    @Test
    public void whenTryToAddDuplicatedStationThenLengthIs1() {
        Station station = new Station(city, TransportType.AUTO);
        city.addStation(station);
        city.addStation(station);
        assertEquals(1, city.getStations().size());
    }

    @Test
    public void whenRemoveValidStationThenSuccess() {
        Station station = new Station(city, TransportType.AUTO);
        city.addStation(station);
        assertTrue(containsStation(city, station));
        city.removeStation(station);
        assertEquals(city.getStations().size(), 0);
    }

    @Test
    public void whenTryToRemoveNullStationThenLengthIs0() {
        assertThrows(NullPointerException.class, () -> city.removeStation(null));
    }

    private boolean containsStation(@NonNull final City city, @NonNull final Station station) {
        return city.getStations().contains(station);
    }
}