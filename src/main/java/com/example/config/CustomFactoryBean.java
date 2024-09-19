package com.example.config;


import com.example.model.Animal;

import org.springframework.beans.factory.FactoryBean;

public class CustomFactoryBean implements FactoryBean<Animal> {
    @Override
    public Animal getObject() throws Exception {
        System.out.println("get Object ne");
        Animal animal=new Animal();
        animal.setName("bird");
        animal.setEnvironment("sky");
        return animal;
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }


    public String getFactoryBeanName(){
        return "customFactoryBean";
    }
}
