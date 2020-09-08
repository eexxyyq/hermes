package ru.eexxyyq.hermes.app.service.helpers;

import ru.eexxyyq.hermes.app.common.utils.ReflectionUtils;

import java.util.List;

/**
 * @author yatixonov
 * @created 08/09/2020 - 10:03
 * @project hermes
 */

public class FieldProvider {
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        return ReflectionUtils.findSimilarFields(source, dest);
    }
}
