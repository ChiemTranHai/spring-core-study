package com.example.service.impl;

import com.example.annotation.Timer;
import com.example.event.BlockedListEvent;
import com.example.tracking.UsageTracked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailServiceImpl implements ApplicationEventPublisherAware {
    @Value("#{${blocked.list:}}")
    private List<String> blockedList;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Timer
    public void sendMail(String mail, String message) {
        blockedList = null;
        try {
            if (blockedList.contains(mail)) {
                System.out.println("contains blocked list");
                applicationEventPublisher.publishEvent(new BlockedListEvent(this, mail, message));
                return;
            }
            System.out.println("send mail success");
        } catch (Exception ignored) {
        }
    }

    public String getMail() {
        System.out.println("get mail before");
        try {
        return blockedList.get(0);
        } catch (Exception ignored) {
            return "";
        }
    }
}
