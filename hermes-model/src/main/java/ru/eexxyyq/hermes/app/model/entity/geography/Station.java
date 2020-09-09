package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.model.search.criteria.StationSearchCriteria;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:38
 * @project hermes
 */
@Table(name = "STATION")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Station extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Embedded
    private Address address;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "TRANSPORT_TYPE")
    private TransportType transportType;

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
