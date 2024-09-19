package com.example.config;

import com.example.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy()
@Profile("prod")
public class TestConfig {

    @PostConstruct
    public void init(){
        System.out.println("TestConfig");
    }

    @Bean
    @Qualifier("testClass1")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TestClass getTestClass() {
        return new TestClass();
    }

    @Bean
    @Qualifier("testClass2")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
    public TestClass2 getTestClass2() {
        return new TestClass2();
    }

    @Bean
    @Primary
    public TestClass1 getTestClass1(@Qualifier("testClass2") ITestClass testClass) {
        System.out.println("vo day non");
        return new TestClass1(testClass);
    }

    @Bean
    public Person instancePerson(){
        return new Person();
    }
}
