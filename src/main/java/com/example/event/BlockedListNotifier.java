package com.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BlockedListNotifier implements ApplicationListener<BlockedListEvent> {
    @Override
    public void onApplicationEvent(BlockedListEvent event) {
        System.out.println("notifier ne " + event);

    }
}
