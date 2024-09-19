package com.example.model;

import jakarta.annotation.PostConstruct;

import java.util.Random;

public class Person {

    private String name;
    private String gentle;
    private int age;

    @PostConstruct
    public void init(){
        System.out.println("post construct person");
    }

    public Person() {
        this.name = "jack";
        this.gentle = "male";
        this.age = 25;
    }

    public void initMethod(){
        System.out.println("init bean person "+new Random().nextInt());
    }

    public void destroyMethod(){
        System.out.println("destroy bean person "+new Random().nextInt());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGentle() {
        return gentle;
    }

    public void setGentle(String gentle) {
        this.gentle = gentle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gentle='" + gentle + '\'' +
                ", age=" + age +
                '}';
    }
}
