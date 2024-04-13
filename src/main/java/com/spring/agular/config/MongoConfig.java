//package com.spring.agular.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "mongocourse";
//    }
//
//    @Bean
//    public MongoClient mongoClient(){
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://mongodbjavaspring05:CASA_JAVA@cluster0.fzg9tyo.mongodb.net");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Override
//    public Collection<String> getMappingBasePackages(){
//        return Collections.singleton("course");
//    }
//
//
//}
//
