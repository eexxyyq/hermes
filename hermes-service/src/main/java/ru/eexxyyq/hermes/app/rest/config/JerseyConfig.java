package ru.eexxyyq.hermes.app.rest.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author yatixonov
 * @created 04/09/2020 - 17:39
 * @project hermes
 */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ru.eexxyyq.hermes.app.rest");
    }
}
