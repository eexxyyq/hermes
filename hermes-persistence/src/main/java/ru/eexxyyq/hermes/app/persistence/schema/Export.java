package ru.eexxyyq.hermes.app.persistence.schema;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import ru.eexxyyq.hermes.app.model.entity.geography.Address;
import ru.eexxyyq.hermes.app.model.entity.geography.City;
import ru.eexxyyq.hermes.app.model.entity.geography.Coordinate;
import ru.eexxyyq.hermes.app.model.entity.geography.Station;
import ru.eexxyyq.hermes.app.model.entity.person.Account;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author yatixonov
 * @created 09/09/2020 - 17:40
 * @project hermes
 */

public class Export {
    public static void exportDatabase(String folder, Class<? extends Dialect> dialect) {
        MetadataSources metadata =
                new MetadataSources(
                        new StandardServiceRegistryBuilder()
                                .applySetting("hibernate.dialect", dialect.getName())
                                .build());

        Set<Class<?>> entityClasses =
                Set.of(City.class, Address.class, Station.class, Coordinate.class, Account.class);
        entityClasses.forEach(metadata::addAnnotatedClass);

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(folder + "schema_" + dialect.getSimpleName() + ".sql");
        schemaExport.create(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
    }

    public static void main(String[] args) {
        exportDatabase("", PostgreSQL10Dialect.class);
    }
}
