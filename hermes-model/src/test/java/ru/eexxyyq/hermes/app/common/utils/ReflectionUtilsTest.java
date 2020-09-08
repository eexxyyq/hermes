package ru.eexxyyq.hermes.app.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.common.exception.ConfigurationException;
import ru.eexxyyq.hermes.app.common.exception.InvalidParametersException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 07/09/2020 - 12:49
 * @project hermes
 */

class ReflectionUtilsTest {
    @Test
    void createInstanceSuccess() {
        Object value = ReflectionUtils.createInstance(Source.class);
        assertNotNull(value);
    }

    @Test
    void createInstanceFailure() {
        assertThrows(ConfigurationException.class, () -> ReflectionUtils.createInstance(Restricted.class));
    }

    @Test
    void findSimilarFieldsSuccess() {
        List<String> fields = ReflectionUtils.findSimilarFields(Source.class,
                Destination.class);
        assertNotNull(fields);
        assertTrue(fields.contains("value"));
    }

    @Test
    void copyFieldsSuccess() {
        Source src = new Source();
        src.setValue(10);
        Destination dest = new Destination();
        List<String> fields = ReflectionUtils.findSimilarFields(src.getClass(), dest.getClass());

        ReflectionUtils.copyFields(src, dest, fields);
        assertEquals(10, dest.getValue());
    }

    @Test
    void copyFieldsDestinationNullFailure() {
        Source src = new Source();
        assertThrows(InvalidParametersException.class,
                () -> ReflectionUtils.copyFields(src, null, Collections.emptyList()));
    }

    static class Source {
        @Setter
        private int value;

        private String text;
    }

    static class Destination {
        @Getter
        private int value;
    }

    static class Restricted {
        public Restricted(int value) {
        }
    }
}

