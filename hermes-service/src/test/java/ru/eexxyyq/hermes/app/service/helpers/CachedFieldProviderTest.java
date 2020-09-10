package ru.eexxyyq.hermes.app.service.helpers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.eexxyyq.hermes.app.common.utils.ReflectionUtils;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
/**
 * @author yatixonov
 * @created 08/09/2020 - 10:31
 * @project hermes
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ReflectionUtils.class)
public class CachedFieldProviderTest {
    private FieldProvider provider;

    @Before
    public void setup() {
        provider = new CachedFieldProvider();
    }

    @org.junit.Test
    public void testGetFieldNamesSuccess() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields.isEmpty());
        assertTrue(fields.contains("value"));
    }

    @org.junit.Test
    public void testGetFieldNamesCachedSuccess() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);
        List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

    @Test
    public void testGetFieldNamesAreCached() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);

        PowerMockito.mockStatic(ReflectionUtils.class);
        when(ReflectionUtils.findSimilarFields(Source.class, Destination.class)).thenReturn(Collections.emptyList());

        assertTrue(ReflectionUtils.findSimilarFields(Source.class, Destination.class).isEmpty());
        List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

    static class Source {
        int value;
    }

    static class Destination {
        int value;
    }
}


