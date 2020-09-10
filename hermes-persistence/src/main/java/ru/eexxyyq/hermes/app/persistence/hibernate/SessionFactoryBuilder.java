package ru.eexxyyq.hermes.app.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import ru.eexxyyq.hermes.app.common.exception.PersistenceException;
import ru.eexxyyq.hermes.app.persistence.interceptor.TimeStampInterceptor;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

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
        Reflections reflections = new Reflections("ru.eexxyyq.hermes.app.model.entity");
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        entityClasses.forEach(sources::addAnnotatedClass);

        org.hibernate.boot.SessionFactoryBuilder builder = sources.getMetadataBuilder()
                .build().getSessionFactoryBuilder()
                .applyInterceptor(new TimeStampInterceptor());
        this.sessionFactory = builder.build();
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
