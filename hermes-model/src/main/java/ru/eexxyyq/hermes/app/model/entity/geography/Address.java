package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:34
 * @project hermes
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {
    private String zipCode;

    private String street;

    /**
     * (Optional) if it's address of apartment
     */
    private String apartment;
}
