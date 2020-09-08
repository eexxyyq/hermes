package ru.eexxyyq.hermes.app.model.search.criteria;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;

import java.util.Objects;

/**
 * @author yatixonov
 * @created 03/09/2020 - 22:44
 * @project hermes
 */
@Getter
@Setter
public class StationSearchCriteria {
    private String name;
    private TransportType transportType;
    private String address;

    public static StationSearchCriteria byName(@NonNull final String name) {
        return new StationSearchCriteria(name);
    }

    public static StationSearchCriteria byTransportType(@NonNull final TransportType type) {
        return new StationSearchCriteria(type);
    }

    public StationSearchCriteria() {

    }

    private StationSearchCriteria(@NonNull final String name) {
        this.name = Objects.requireNonNull(name);
    }

    private StationSearchCriteria(@NonNull final TransportType type) {
        this.transportType = Objects.requireNonNull(type);
    }
}
