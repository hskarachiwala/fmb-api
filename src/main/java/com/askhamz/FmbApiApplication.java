package com.askhamz;

import com.askhamz.api.MenuItemApi;
import com.askhamz.core.MenuItemService;
import com.askhamz.db.MenuItemDao;
import com.askhamz.db.MenuItemDaoImpl;
import com.askhamz.db.MongoConfig;
import com.askhamz.health.TemplateHealthCheck;
import com.askhamz.resources.HelloWorldResource;
import com.askhamz.resources.MenuItemResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import dev.morphia.Morphia;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.UuidRepresentation;
import org.bson.codecs.UuidCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class FmbApiApplication extends Application<FmbApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FmbApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "fmb-api";
    }

    @Override
    public void initialize(final Bootstrap<FmbApiConfiguration> bootstrap) {
        bootstrap.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(final FmbApiConfiguration configuration, final Environment environment) {

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        MongoConfig mongoConfig = configuration.getMongoConfig();
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD)), MongoClient.getDefaultCodecRegistry());
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoConfig.getServer(), mongoConfig.getPort()), MongoClientOptions.builder().codecRegistry(codecRegistry).build());

        JerseyEnvironment jersey = environment.jersey();
        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(MenuItemService.class).to(MenuItemApi.class).in(Singleton.class);
                bind(MenuItemDaoImpl.class).to(MenuItemDao.class).in(Singleton.class);
                bind(Morphia.class).to(Morphia.class).in(Singleton.class);

                bind(mongoClient).to(MongoClient.class);
                bind(environment.getObjectMapper()).to(ObjectMapper.class);

                bind(configuration.getMongoConfig().getDbName()).to(String.class).named("DB_NAME");
            }
        });
        jersey.register(MenuItemResource.class);
    }
}
