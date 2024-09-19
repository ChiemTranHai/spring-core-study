package com.example.model;

import java.util.Random;

public class Car {
    private String type;
    private String name;
    private String branch;
    private String color;

    public Car() {
        System.out.println("constructor car");
        this.type = "sedan";
        this.name = "Yaris Cross";
        this.branch = "Toyota";
        this.color = "black";
    }

    public void initMethod(){
        System.out.println("init bean car "+new Random().nextInt());
    }

    public static void destroyMethod(){
        System.out.println("destroy bean car "+new Random().nextInt());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
