package org.worldbuild.kafka.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.SocketSettings;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@Log4j2
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private static final String UTF_8 = "UTF-8";
    private static final String MONGODB_PREFIX = "mongodb://";
    private static final String MONGODB_SRV_PREFIX = "mongodb+srv://";
    private final MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    @Primary
    @Override
    public com.mongodb.client.MongoClient mongoClient() {
        log.info("Primary MongoClient instantiated");
        SocketSettings.builder().connectTimeout(2, TimeUnit.MINUTES);
        MongoCredential mongoCredential = MongoCredential.createCredential(
                mongoProperties.getUsername(),
                mongoProperties.getAuthenticationDatabase(),
                mongoProperties.getPassword());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(MONGODB_PREFIX + mongoProperties.getHost()))
                .credential(mongoCredential)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    @Primary
    @Override
    public MongoDbFactory mongoDbFactory() {
        log.info("Primary MongoDbFactory instantiated");
        return new SimpleMongoClientDbFactory(mongoClient(), getDatabaseName());
    }

    @Bean
    @Primary
    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        log.info("Primary MongoTemplate instantiated");
        return new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    public CustomConversions customConversions() {
        log.info("Custom converter instantiated");
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        return new MongoCustomConversions(converterList);
    }

}
