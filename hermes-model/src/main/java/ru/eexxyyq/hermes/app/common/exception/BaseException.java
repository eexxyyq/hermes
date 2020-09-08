package ru.eexxyyq.hermes.app.common.exception;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:13
 * @project hermes
 */

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 7837501112802868980L;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }
}
