package ru.eexxyyq.hermes.app.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import ru.eexxyyq.hermes.app.common.exception.PersistenceException;
import ru.eexxyyq.hermes.app.model.entity.geography.Address;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.entity.person.Account;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yatixonov
 * @created 09/09/2020 - 22:37
 * @project hermes
 */

public class SessionFactoryBuilder {
    private final SessionFactory sessionFactory;

    public SessionFactoryBuilder() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(loadProperties()).build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(Address.class);
        sources.addAnnotatedClass(City.class);
        sources.addAnnotatedClass(Station.class);
        sources.addAnnotatedClass(Account.class);
        this.sessionFactory = sources.buildMetadata().buildSessionFactory();
    }

    private Properties loadProperties() {
        try {
            InputStream in = SessionFactoryBuilder.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @PreDestroy
    public void destroy() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
        }
    }
}
