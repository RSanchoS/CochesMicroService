package com.roberto.CochesMicroService;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig {


    @Value("${mongo.nameDB}")
    private String nameDB;

    @Bean
    public MongoDbFactory mongoDbFactory(){
        return new SimpleMongoDbFactory(new MongoClient(), this.nameDB);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(this.mongoDbFactory());
    }

}
