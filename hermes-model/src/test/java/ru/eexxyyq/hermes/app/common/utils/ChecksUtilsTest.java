package ru.eexxyyq.hermes.app.common.utils;

import org.junit.jupiter.api.Test;
import ru.eexxyyq.hermes.app.common.exception.InvalidParametersException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:29
 * @project hermes
 */

class ChecksUtilsTest {

    @Test
    void whenCheckInvalidParametersThenException() {
        assertThrows(InvalidParametersException.class,
                () -> ChecksUtils.checkParameter(false, "Invalid params"));
        assertEquals("Invalid params", assertThrows(InvalidParametersException.class,
                () -> ChecksUtils.checkParameter(false, "Invalid params")).getMessage());
    }

    @Test
    void whenCheckValidParametersThenNoException() {
        ChecksUtils.checkParameter(true, "Correct parameters");
        assertTrue(true);
    }
}