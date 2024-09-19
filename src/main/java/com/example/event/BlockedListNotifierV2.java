package com.example.event;

import com.example.service.impl.MailServiceImpl;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BlockedListNotifierV2 {

    @EventListener(classes = BlockedListEvent.class)
    public void onApplicationListener(BlockedListEvent event){
        System.out.println("V2 "+event);
    }
}
