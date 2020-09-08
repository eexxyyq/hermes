package ru.eexxyyq.hermes.app.service.transform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.common.exception.InvalidParametersException;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.rest.dto.CityDTO;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 07/09/2020 - 13:32
 * @project hermes
 */

class SimpleDTOTransformerImplTest {
    private Transformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new SimpleDTOTransformerImpl();
    }

    @Test
    void testTransformationCitySuccess() {
        City city = new City("Moscow");
        city.setId(1L);
        city.setRegion("Msc");
        city.setDistrict("None");

        CityDTO cityDTO = transformer.transform(city, CityDTO.class);

        assertNotNull(cityDTO);
        assertEquals(cityDTO.getId(), city.getId());
        assertEquals(cityDTO.getName(), city.getName());
        assertEquals(cityDTO.getDistrict(), city.getDistrict());
        assertEquals(cityDTO.getRegion(), city.getRegion());
    }

    @Test
    void testTransformationNullCityFailed() {
        assertThrows(InvalidParametersException.class, () -> transformer.transform(null, CityDTO.class));
    }

    @Test
    void testTransformationNullDestClassFailed() {
        assertThrows(InvalidParametersException.class, () -> transformer.transform(new City("Moscow"), null));
    }
    
    @Test
    void testUntransormCitySuccess() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setName("Moscow");
        cityDTO.setDistrict("None");
        cityDTO.setRegion("Msc");
        
        City city = transformer.untransform(cityDTO, City.class);
        assertNotNull(city);
        assertEquals(cityDTO.getId(), city.getId());
        assertEquals(cityDTO.getName(), city.getName());
        assertEquals(cityDTO.getDistrict(), city.getDistrict());
        assertEquals(cityDTO.getRegion(), city.getRegion());
    }   

    @Test
    void testUntransformationNullCityFailed() {
        assertThrows(InvalidParametersException.class, () -> transformer.untransform(null, City.class));
    }

    @Test
    void testUntransformationNullDestClassFailed() {
        assertThrows(InvalidParametersException.class, () -> transformer.untransform(new CityDTO(), null));
    }


}