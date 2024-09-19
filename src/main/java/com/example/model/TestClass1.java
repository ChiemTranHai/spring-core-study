package com.example.model;


import jakarta.annotation.PostConstruct;

public class TestClass1 {
    private final ITestClass testClass;

    @PostConstruct
    public void init(){
        System.out.println("TestClass1");
    }

    public TestClass1(ITestClass testClass) {
//        System.out.println("test class1 constructor "+new Random().nextInt());
        this.testClass = testClass;
    }

    public String getName(){
        return testClass.getName();
    }
}
