package ru.eexxyyq.hermes.app.common.utils;

import ru.eexxyyq.hermes.app.common.exception.InvalidParametersException;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:29
 * @project hermes
 */

public interface ChecksUtils {
   static void checkParameter(boolean condition, String message) {
       if (!condition) {
           throw new InvalidParametersException(message);
       }
   }
}
