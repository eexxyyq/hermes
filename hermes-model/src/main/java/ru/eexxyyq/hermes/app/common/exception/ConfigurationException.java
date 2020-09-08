package ru.eexxyyq.hermes.app.common.exception;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:17
 * @project hermes
 */

public class ConfigurationException extends BaseException {

    private static final long serialVersionUID = -2177284893894040026L;

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationException(String message) {
        super(message);
    }
}
