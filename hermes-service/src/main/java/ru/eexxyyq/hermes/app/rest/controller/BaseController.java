package ru.eexxyyq.hermes.app.rest.controller;

import javax.ws.rs.core.Response;

/**
* @created 07/09/2020 - 17:03
* @project hermes
* @author yatixonov
*/

public abstract class BaseController {

    protected final Response NOT_FOUND;
    protected final Response BAD_REQUEST;

    public BaseController() {
        this.NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();
        this.BAD_REQUEST = Response.status(Response.Status.BAD_REQUEST).build();
    }

    protected Response ok(Object result) {
        return Response.ok(result).build();
    }
}
