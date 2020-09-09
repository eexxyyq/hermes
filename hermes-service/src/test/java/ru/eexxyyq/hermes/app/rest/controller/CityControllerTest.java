package ru.eexxyyq.hermes.app.rest.controller;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import ru.eexxyyq.hermes.app.rest.config.JerseyConfig;
import ru.eexxyyq.hermes.app.rest.dto.CityDTO;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author yatixonov
 * @created 04/09/2020 - 17:36
 * @project hermes
 */

public class CityControllerTest extends JerseyTest {
    public CityControllerTest() {
    }

    @Override
    protected Application configure() {
        return new JerseyConfig();
    }

    @Test
    public void findCitiesTestSuccess() {
        List<Map<String, String>> cities = target("cities").request().get(List.class);
        assertNotNull(cities);
        assertEquals(1, cities.size());
        Map<String, String> city = cities.get(0);
        assertEquals("Moscow", city.get("name"));
    }

    @Test
    public void testFindCityByIdSuccess() {
        CityDTO city = target("cities/1").request().get(CityDTO.class);
        assertNotNull(city);
        assertEquals(1, city.getId());
        assertEquals("Moscow", city.getName());
    }

    @Test
    public void testSaveCitySuccess() {
        CityDTO city = new CityDTO();
        city.setName("Kiev");
        city.setDistrict("Ukr");
        city.setRegion("Ukr");

        Response response = target("cities").request().post(Entity.entity(city, MediaType.APPLICATION_JSON));
        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
    }
}