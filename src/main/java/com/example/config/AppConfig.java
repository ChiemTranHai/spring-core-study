package com.example.config;

import com.example.model.Car;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Lookup;


public abstract class AppConfig {

    public void initMethod(){
        System.out.println("init app config");
    }

    @Lookup("car")
    public abstract Car getCar();

    public Person getPerson(){
        return new Person();
    }
}
