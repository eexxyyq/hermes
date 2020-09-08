package ru.eexxyyq.hermes.app.service.transform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eexxyyq.hermes.app.common.utils.ChecksUtils;
import ru.eexxyyq.hermes.app.common.utils.CommonUtils;
import ru.eexxyyq.hermes.app.common.utils.ReflectionUtils;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;
import ru.eexxyyq.hermes.app.rest.dto.BaseDTO;
import ru.eexxyyq.hermes.app.service.helpers.CachedFieldProvider;
import ru.eexxyyq.hermes.app.service.helpers.FieldProvider;

/**
 * @author yatixonov
 * @created 07/09/2020 - 13:25
 * @project hermes
 */

public class SimpleDTOTransformerImpl implements Transformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDTOTransformerImpl.class);
    private final FieldProvider fieldProvider;

    public SimpleDTOTransformerImpl() {
        this.fieldProvider = new CachedFieldProvider();
    }

    @Override
    public <T extends BaseEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clz) {
        checkParams(entity, clz);
        P dto = ReflectionUtils.createInstance(clz);
        ReflectionUtils.copyFields(entity, dto, fieldProvider.getFieldNames(entity.getClass(), clz));
        dto.transform(entity);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransformer.transform: {} DTO object",
                    CommonUtils.toString(dto));
        }

        return dto;
    }

    @Override
    public <T extends BaseEntity, P extends BaseDTO<T>> T untransform(P dto, Class<T> clz) {
        checkParams(dto, clz);
        T entity = ReflectionUtils.createInstance(clz);
        ReflectionUtils.copyFields(dto, entity, fieldProvider.getFieldNames(dto.getClass(), clz));
        dto.untransform(entity);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SimpleDTOTransformer.transform: {} entity",
                    CommonUtils.toString(dto));
        }

        return entity;
    }

    private void checkParams(final Object param, final Class<?> clz) {
        ChecksUtils.checkParameter(param != null, "Source transformation object is not initialized");
        ChecksUtils.checkParameter(clz != null, "No class is defined for transformation");
    }
}
