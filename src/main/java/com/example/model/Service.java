package com.example.model;

import jakarta.enterprise.inject.Alternative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

public class Service {
//    @Autowired
//    @Qualifier("action1")
    private final Map<String, Service1> service1;
    private Service2 service2;

//    @Autowired
    private Service(@Qualifier("action1") Map<String,Service1> service1) {
        this.service1 = service1;
    }

    @Autowired
    @Qualifier("action1")
    private void setService2(Service2 service2){
        this.service2=service2;
    }

    public void printMap(){
        for (Map.Entry<String, Service1> entry : service1.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public String getName(){
        return "--"+service2.getName();
    }
}
