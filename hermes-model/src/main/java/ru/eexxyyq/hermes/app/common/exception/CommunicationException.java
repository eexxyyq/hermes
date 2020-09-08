package ru.eexxyyq.hermes.app.common.exception;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:16
 * @project hermes
 */

public class CommunicationException extends BaseException {

    private static final long serialVersionUID = -2850898723336164866L;

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommunicationException(String message) {
        super(message);
    }
}
