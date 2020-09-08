package ru.eexxyyq.hermes.app.common.exception;

/**
 * @author yatixonov
 * @created 03/09/2020 - 23:20
 * @project hermes
 */

public class InvalidParametersException extends FlowException {

    private static final long serialVersionUID = 4990959228756992926L;

    public InvalidParametersException(String message) {
        super(message);
    }
}
