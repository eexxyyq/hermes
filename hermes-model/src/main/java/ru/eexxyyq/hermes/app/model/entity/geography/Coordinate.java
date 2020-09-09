package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:40
 * @project hermes
 */


@Embeddable
@Data
public class Coordinate {
    private Double x;
    private Double y;
}
