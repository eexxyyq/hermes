package ru.eexxyyq.hermes.app.common.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author yatixonov
 * @created 03/09/2020 - 16:22
 * @project hermes
 */

public interface CommonUtils {
    static <T> Set<T> getSafeSet(Set<T> sourceSet) {
        return Collections.unmodifiableSet(Optional.ofNullable(sourceSet).orElse(Collections.emptySet()));
    }

    static <T> List<T> getSafeList(List<T> sourceList) {
        return Collections.unmodifiableList(Optional.ofNullable(sourceList).orElse(Collections.emptyList()));
    }

    static String toString(Object param) {
        return ReflectionToStringBuilder.toString(param,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
