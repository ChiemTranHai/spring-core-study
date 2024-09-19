package com.example.aop;

import com.example.tracking.UsageTracked;
import com.example.tracking.impl.DefaultUsageTracked;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UsageTracking {

    @Pointcut("within(com.example.service.impl.MailServiceImpl)")
    private void mailServicePointCut() {
    }

    @Pointcut("within(com.example.service.impl.PersonServiceImpl)")
    private void personServicePointCut() {
    }

    @DeclareParents(value = "com.example.service.impl.*+", defaultImpl = DefaultUsageTracked.class)
    private UsageTracked usageTracked;

    @After(value = "(mailServicePointCut() || personServicePointCut()) && this(usageTracked)", argNames = "joinPoint,usageTracked")
    private void recordUsage(JoinPoint joinPoint, UsageTracked usageTracked) {
        System.out.println("-------Start record---------");
        usageTracked.incrementUseCount(joinPoint.getSignature().getName());
        System.out.println("-------End record---------------------");
    }

}
