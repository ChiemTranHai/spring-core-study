package com.example.config;

import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void processContextRefreshedEvent() {
        System.out.println("processContextRefreshedEvent ");
    }

    @EventListener
    public void processContextStartedEvent(ContextStartedEvent event) {
        System.out.println("processContextStartedEvent");
    }

    @EventListener
    public void processContextStoppedEvent(ContextStoppedEvent event) {
        System.out.println("processContextStoppedEvent");
    }

    @EventListener
    public void processContextClosedEvent(ContextClosedEvent event) {
        System.out.println("processContextClosedEvent");
    }
}
