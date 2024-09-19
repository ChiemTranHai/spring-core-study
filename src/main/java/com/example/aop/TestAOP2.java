package com.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
@Aspect
public class TestAOP2 {
    @Pointcut("within(com.example.service.impl.PersonServiceImpl)")
    private void personServicePointCut(){}

    @Before("personServicePointCut()")
    private void auditInsertPersonBefore3(){
        System.out.println("auditInsetPersonBefore hjhjhjhjh");
    }
}
