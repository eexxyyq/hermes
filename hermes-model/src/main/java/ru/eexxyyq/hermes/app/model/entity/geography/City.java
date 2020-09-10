package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.*;
import ru.eexxyyq.hermes.app.common.utils.CommonUtils;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.model.entity.transport.TransportType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:36
 * @project hermes
 */
@Table(name = "CITY")
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "stations")
@NoArgsConstructor
public class City extends BaseEntity {
    public static final String FIELD_NAME = "name";
    @Getter
    @Setter
    @Column(name = "NAME", nullable = false, length = 32)
    private String name;

    @Getter
    @Setter
    @Column(name = "DISTRICT", nullable = false, length = 32)
    private String district;

    @Getter
    @Setter
    @Column(name = "REGION", nullable = false, length = 32)
    private String region;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "city", orphanRemoval = true)
    private Set<Station> stations = new HashSet<>();

    public City(String name) {
        this.name = name;
    }

    public Set<Station> getStations() {
        return CommonUtils.getSafeSet(stations);
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
