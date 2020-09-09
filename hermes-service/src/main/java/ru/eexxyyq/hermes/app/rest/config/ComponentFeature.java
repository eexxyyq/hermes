package ru.eexxyyq.hermes.app.rest.config;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * @author yatixonov
 * @created 09/09/2020 - 16:38
 * @project hermes
 */

public class ComponentFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(new ComponentBinder());
        return true;
    }
}
