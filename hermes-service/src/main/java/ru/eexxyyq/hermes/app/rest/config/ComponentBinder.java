package ru.eexxyyq.hermes.app.rest.config;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.eexxyyq.hermes.app.persistence.repository.CityRepository;
import ru.eexxyyq.hermes.app.persistence.repository.InMemoryCityRepositoryImpl;
import ru.eexxyyq.hermes.app.service.geographic.GeographicService;
import ru.eexxyyq.hermes.app.service.geographic.GeographicServiceImpl;
import ru.eexxyyq.hermes.app.service.transform.SimpleDTOTransformerImpl;
import ru.eexxyyq.hermes.app.service.transform.Transformer;

import javax.inject.Singleton;

/**
 * @author yatixonov
 * @created 09/09/2020 - 16:35
 * @project hermes
 */

public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(InMemoryCityRepositoryImpl.class).to(CityRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformerImpl.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
    }
}
