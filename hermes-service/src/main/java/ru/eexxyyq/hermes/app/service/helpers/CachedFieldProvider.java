package ru.eexxyyq.hermes.app.service.helpers;

import ru.eexxyyq.hermes.app.common.utils.ReflectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yatixonov
 * @created 08/09/2020 - 10:05
 * @project hermes
 */

public class CachedFieldProvider extends FieldProvider {
    private final Map<String, List<String>> cacheMap;

    public CachedFieldProvider() {
        this.cacheMap = new HashMap<>();
    }

    @Override
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        String key = source.getSimpleName() + dest.getSimpleName();
        List<String> fields = this.cacheMap.get(key);

        if (fields == null) {
            fields = ReflectionUtils.findSimilarFields(source, dest);
            cacheMap.put(key, fields);
        }

        return fields;
    }
}
