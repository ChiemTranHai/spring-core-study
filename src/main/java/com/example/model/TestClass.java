package com.example.model;

import java.util.Random;

public class TestClass implements ITestClass {

    private final Integer randomNumber;

    public TestClass() {
        this.randomNumber= new Random().nextInt();
//        System.out.println("create test class constructor " + randomNumber);
    }

    public String getName() {
        return "test class "+randomNumber;
    }
}
