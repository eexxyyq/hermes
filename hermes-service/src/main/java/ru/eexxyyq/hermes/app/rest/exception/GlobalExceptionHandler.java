package ru.eexxyyq.hermes.app.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author yatixonov
 * @created 07/09/2020 - 17:31
 * @project hermes
 */

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final Response SERVER_ERROR;

    public GlobalExceptionHandler() {
        this.SERVER_ERROR = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return SERVER_ERROR;
    }
}
