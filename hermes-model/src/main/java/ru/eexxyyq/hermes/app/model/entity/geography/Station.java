package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;

import java.util.Objects;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:38
 * @project hermes
 */

@Getter
@EqualsAndHashCode(callSuper = true)
public class Station extends BaseEntity {
    private final City city;

    @Setter
    private Address address;

    @Setter
    private Coordinate coordinate;

    private final TransportType transportType;

    public Station(City city, TransportType transportType) {
        this.city = city;
        this.transportType = transportType;
    }

    public boolean match(final StationSearchCriteria criteria) {
        Objects.requireNonNull(criteria, "Station criteria is not defined");

        if (!StringUtils.isEmpty(criteria.getName()) && !this.city.getName().equals(criteria.getName())) {
                return false;
        }

        return Objects.isNull(criteria.getTransportType()) || this.transportType.equals(criteria.getTransportType());
    }
}
