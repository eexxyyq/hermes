package ru.eexxyyq.hermes.app.common.exception;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:18
 * @project hermes
 */

public class FlowException extends BaseException {

    private static final long serialVersionUID = -2889607185988464072L;

    public FlowException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowException(String message) {
        super(message);
    }
}
