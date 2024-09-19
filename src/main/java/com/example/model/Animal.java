package com.example.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Animal implements InitializingBean {

    private String name;
    private String type;
    private String age;
    private String environment;

    public  Animal(){}

    public Animal(String name, String type, String age, String environment) {
        System.out.println("Animal 1");
        this.name = name;
        this.type = type;
        this.age = age;
        this.environment = environment;
    }

    public Animal(String type, String age, String environment) {
        System.out.println("Animal 2");
        this.type = type;
        this.age = age;
        this.environment = environment;
    }

    public Animal(String age, String environment) {
        System.out.println("Animal 3");
        this.age = age;
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("int animal ne");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age='" + age + '\'' +
                ", environment='" + environment + '\'' +
                '}';
    }
}
