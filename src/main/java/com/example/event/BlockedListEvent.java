package com.example.event;

import org.springframework.context.ApplicationEvent;

public class BlockedListEvent extends ApplicationEvent {
    private String address;
    private String message;

    public BlockedListEvent(Object source, String address, String message) {
        super(source);
        this.address = address;
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BlockedListEvent{" +
                "address='" + address + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
