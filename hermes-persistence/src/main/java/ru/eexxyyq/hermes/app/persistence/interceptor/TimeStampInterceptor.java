package ru.eexxyyq.hermes.app.persistence.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import ru.eexxyyq.hermes.app.model.entity.base.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author yatixonov
 * @created 10/09/2020 - 10:27
 * @project hermes
 */

public class TimeStampInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 6825201844452552L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        int index = Arrays.asList(propertyNames).indexOf(BaseEntity.FIELD_CREATED_AT);
        if (index >= 0) {
            state[index] = LocalDateTime.now();
            return true;
        }
        return false;
    }
}
