package ru.eexxyyq.hermes.app.common.utils;

import ru.eexxyyq.hermes.app.common.exception.ConfigurationException;
import ru.eexxyyq.hermes.app.common.utils.annotation.Ignore;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yatixonov
 * @created 07/09/2020 - 10:19
 * @project hermes
 */

public class ReflectionUtils {
    private ReflectionUtils() {
    }

    public static <T> T createInstance(Class<T> clz) {
        try {
            return clz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }

    public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) {
        List<Field> fields = getFields(clz1);
        List<String> targetFields = getFields(clz2).stream()
                .filter(field -> !field.isAnnotationPresent(Ignore.class))
                .map(Field::getName)
                .collect(Collectors.toList());
        return fields.stream()
                .filter(field -> !field.isAnnotationPresent(Ignore.class))
                .filter(field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()))
                .map(Field::getName)
                .filter(targetFields::contains)
                .collect(Collectors.toList());
    }

    public static List<Field> getFields(Class<?> clz) {
        List<Field> fields = new ArrayList<>();
        while (clz != null) {
            fields.addAll(Arrays.asList(clz.getDeclaredFields()));
            clz = clz.getSuperclass();
        }
        return fields;
    }

    public static void copyFields(Object source, Object dest, List<String> fields) {
        ChecksUtils.checkParameter(source != null, "Source object is not initialized yet");
        ChecksUtils.checkParameter(dest != null, "Destination object is not initialized yet");
        try {
            for (String field : fields) {
                Field fieldSource = getField(source.getClass(), field);
                if (fieldSource != null && fieldSource.trySetAccessible()) {
                    Object value = fieldSource.get(source);
                    Field fieldDest = getField(dest.getClass(), field);
                    if (fieldDest != null && fieldDest.trySetAccessible()) {
                        fieldDest.set(dest, value);
                    }
                }
            }
        } catch (SecurityException | ReflectiveOperationException
                | IllegalArgumentException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }

    public static <T> Field getField(final Class<T> clz, final String name) {
        Class<?> currentClass = clz;
        while (currentClass != null) {
            try {
                return clz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        return null;
    }
}
