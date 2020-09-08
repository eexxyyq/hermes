package ru.eexxyyq.hermes.app.service.transform;

import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.rest.dto.BaseDTO;

/**
 * @author yatixonov
 * @created 07/09/2020 - 13:21
 * @project hermes
 */

public interface Transformer {
    /**
     * Converts specified entity into DTO object
     * @param entity
     * @param clz
     * @return
     */
    <T extends BaseEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clz);

    /**
     * Converts specified DTO object into business entity
     * @param dto
     * @param clz
     * @return
     */
    <T extends BaseEntity, P extends BaseDTO<T>> T untransform(P dto, Class<T> clz);
}
