package com.example.config;

import com.example.model.ITestClass;
import com.example.model.TestClass;
import com.example.model.TestClass1;
import com.example.model.TestClass2;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration // default use proxy mode cglib (target class)
@Profile("dev")
//@Order(Integer.MIN_VALUE)
public class TestConfigCGLib {

    @PostConstruct
    public void init(){
        System.out.println("TestConfigCGLib");
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TestClass getTestClass() {
        return new TestClass();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TestClass2 getTestClass3() {
        return new TestClass2();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
    public ITestClass getTestClass2() {
        return new TestClass2();
    }

    @Bean
    @Primary
    public TestClass1 getTestClass1() {
        System.out.println("vo day cglib");
        return new TestClass1(getTestClass2());
    }
}
