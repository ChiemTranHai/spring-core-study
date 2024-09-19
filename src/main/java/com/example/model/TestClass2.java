package com.example.model;

import java.util.Random;

public class TestClass2 implements ITestClass{

    private final Integer randomNumber;

    public TestClass2(){
        this.randomNumber=new Random().nextInt();
    }

    @Override
    public String getName() {
        return "test class2 "+randomNumber;
    }
}
