package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:40
 * @project hermes
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Coordinate extends BaseEntity {
    private Double x;
    private Double y;
}
