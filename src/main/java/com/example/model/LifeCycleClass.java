package com.example.model;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;

public class LifeCycleClass implements BeanNameAware,LifecycleProcessor, SmartLifecycle, InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy ne");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init ne");
    }

    private volatile boolean running;

    @Override
    public void start() {
        this.running=true;
        System.out.println("start ne ");
    }

    @Override
    public void stop() {
        this.running=false;
        System.out.println("stop ne");
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void onRefresh() {
        System.out.println("onRefresh");
    }

    @Override
    public void onClose() {
        System.out.println("onClose");
    }

    @Override
    public boolean isAutoStartup() {
        return SmartLifecycle.super.isAutoStartup();
    }

    @Override
    public void stop(Runnable callback) {
        this.running=false;
        SmartLifecycle.super.stop(callback);
    }

    @Override
    public int getPhase() {
        return SmartLifecycle.super.getPhase();
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("name ne "+name);
    }
}
