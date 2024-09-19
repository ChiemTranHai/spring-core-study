package com.example.config;

import com.example.entity.*;
import org.hibernate.CacheMode;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DbConfig {

    @Bean
    SessionFactory getSessionFactory() {
        ServiceRegistry serviceRegistry =new StandardServiceRegistryBuilder()
                .loadProperties("/hibernate.properties").build();
        Metadata metadata = new MetadataSources(serviceRegistry)
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(Category.class)
//                .addAnnotatedClass(Post.class)
//                .addAnnotatedClass(UserProfile.class)
//                .addAnnotatedClass(Role.class)

                .addAnnotatedClass(Environment.class)
                .addAnnotatedClass(FeatureFlag.class)
                .addAnnotatedClass(FeatureFlagInfo.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Variant.class)
                .getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
