package ru.eexxyyq.hermes.app.common.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yatixonov
 * @created 10/09/2020 - 22:54
 * @project hermes
 */

public class ValidationException extends FlowException {
    private static final long serialVersionUID = 6858621613562789296L;

    public <T> ValidationException(String message, Set<ConstraintViolation<T>> constraints) {
        super(message + ":" + constraints.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(",")));
    }
}
