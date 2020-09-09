package ru.eexxyyq.hermes.app.model.entity.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yatixonov
 * @created 03/09/2020 - 15:33
 * @project hermes
 */

@Table(name = "ACCOUNT")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Account extends BaseEntity {
}
