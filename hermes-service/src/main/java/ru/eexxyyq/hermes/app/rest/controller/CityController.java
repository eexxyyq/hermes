package ru.eexxyyq.hermes.app.rest.controller;

import org.apache.commons.lang3.math.NumberUtils;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.rest.dto.CityDTO;
import ru.eexxyyq.hermes.app.service.geographic.GeographicService;
import ru.eexxyyq.hermes.app.service.transform.Transformer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yatixonov
 * @created 04/09/2020 - 17:29
 * @project hermes
 */
@Path("cities")
public class CityController extends BaseController {
    private final GeographicService service;
    private final Transformer transformer;

    @Inject
    public CityController(GeographicService service, Transformer transformer) {
        this.transformer = transformer;
        this.service = service;
        City city = new City("Moscow");
        city.setDistrict("Msc");
        city.setRegion("Msc");
        city.addStation(TransportType.AVIA);
        service.saveCity(city);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CityDTO> findCities() {
        return service
                .findCities()
                .stream()
                .map(city -> transformer.transform(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveCity(CityDTO cityDTO) {
        service.saveCity(transformer.untransform(cityDTO, City.class));
    }

    @Path("/{cityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCityById(@PathParam("cityId") final String cityId) {
        if (!NumberUtils.isCreatable(cityId)) {
            return BAD_REQUEST;
        }

        Optional<City> city = service.findCityById(NumberUtils.toLong(cityId));
        if (city.isEmpty()) {
            return NOT_FOUND;
        }

        return ok(transformer.transform(city.get(), CityDTO.class));
    }
}
