package ru.eexxyyq.hermes.app.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ru.eexxyyq.hermes.app.model.entity.geography.City;

/**
 * @author yatixonov
 * @created 07/09/2020 - 13:32
 * @project hermes
 */

@Getter
@Setter
public class CityDTO extends BaseDTO<City>{
    private String name;

    private String district;

    private String region;
}
