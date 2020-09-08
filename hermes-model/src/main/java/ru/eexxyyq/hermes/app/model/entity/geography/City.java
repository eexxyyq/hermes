package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.*;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;
import ru.eexxyyq.hermes.app.common.utils.CommonUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:36
 * @project hermes
 */
@EqualsAndHashCode(callSuper = true, exclude = "stations")
@NoArgsConstructor
public class City extends BaseEntity {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String district;
    @Getter
    @Setter
    private String region;

    private Set<Station> stations;

    public City(String name) {
        this.name = name;
    }

    public Set<Station> getStations() {
        return CommonUtils.getSafeList(stations);
    }

    public void addStation(@NonNull final Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if (stations == null) {
            stations = new HashSet<>();
        }
        stations.add(station);
    }

    public void addStation(@NonNull final TransportType type) {
        Objects.requireNonNull(type, "type parameter is not initialized");
        if (stations == null) {
            stations = new HashSet<>();
        }
        stations.add(new Station(this, type));
    }

    public void removeStation(@NonNull Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if (stations == null) {
            stations = new HashSet<>();
        }
        if (!stations.isEmpty()) {
            stations.remove(station);
        }
    }

}
