package com.example.model;

import java.util.Random;

public class Service1 {
    Service1(){
        System.out.println("init service 1 ");
    }

    public String getName(){
        return "service "+new Random().nextInt();
    }
}
