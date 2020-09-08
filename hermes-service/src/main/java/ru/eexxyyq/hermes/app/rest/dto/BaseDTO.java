package ru.eexxyyq.hermes.app.rest.dto;

import lombok.Getter;
import lombok.Setter;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;

/**
 * @author yatixonov
 * @created 07/09/2020 - 13:23
 * @project hermes
 */

public abstract class BaseDTO<T extends BaseEntity> {
    @Getter
    @Setter
    private Long id;

    public void transform(T t) {
        id = t.getId();
    }

    public T untransform(T t) {
        t.setId(getId());
        return t;
    }
}
