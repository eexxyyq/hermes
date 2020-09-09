package ru.eexxyyq.hermes.app.model.entity.geography;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:34
 * @project hermes
 */

@Embeddable
@Data
public class Address {
    @Column(name = "ZIP_CODE", length = 10)
    private String zipCode;

    @Column(name = "STREET", length = 10)
    private String street;

    /**
     * (Optional) if it's address of apartment
     */
    @Column(name = "APARTMENT", length = 16)
    private String apartment;
}
