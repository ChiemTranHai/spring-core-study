package com.example.aop;

import com.example.annotation.Auditable;
import com.example.model.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class TestAOP {
    private final static Logger LOGGER= Logger.getLogger(TestAOP.class.getName());

    @Pointcut("execution(* sendMail(..))")
    private void test(){}

    @Pointcut("within(com.example.service..*)")
    private void test1(){}

    @Pointcut("test() && @annotation(com.example.annotation.Timer)")
    private void timerJoinPoint(){}

    @Pointcut("execution(String com.example.service..getMail(..))")
    private void getMailPointCut(){}

    @Pointcut("within(com.example.service.impl.PersonServiceImpl)")
    private void personServicePointCut(){}

    @Pointcut("execution(* com.example..*(..))")
    private void exceptionPointCut(){}

//    @Before("test()")
//    private void sendMailAfter(){
//        System.out.println("vo day aop ne ");
//    }
//
//    @Before("test1()")
//    private void sendMailAfter1(){
//        System.out.println("vo day aop ne 2");
//    }

    @Around("timerJoinPoint()")
    private Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getThis().getClass().getSimpleName());
        System.out.println(joinPoint.getTarget().getClass().getName());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("TIMER: "+stopWatch.getTotalTimeSeconds());
        return result;
    }

    @AfterReturning(pointcut = "getMailPointCut()", returning = "mailName")
    private void getMail(String mailName){
        System.out.println("get mail advice ne "+mailName);
    }

    @After(value = "personServicePointCut() && @annotation(auditable) && args(person)",argNames = "joinPoint,auditable,person")
    private void auditInsertPerson(JoinPoint joinPoint, Auditable auditable, Person person){
        System.out.println("-----------Start Audit----------------");
        System.out.println(auditable.value());
        System.out.println(person);
        System.out.println("-----------End Audit---------------------------");
    }



    @Before("personServicePointCut()")
    private void auditInsertPersonBefore(){
        System.out.println("auditInsetPersonBefore 1");
    }

    @Before("personServicePointCut()")
    private void auditInsertPersonBefore2(){
        System.out.println("auditInsetPersonBefore 2");
    }

//    @Around("personServicePointCut()")
//    private Object auditInsertPersonAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("auditInsertPersonAround");
//        return joinPoint.proceed();
//    }

    @AfterThrowing(pointcut = "exceptionPointCut()", throwing = "exception")
    private void logException(Exception exception){
        LOGGER.log(Level.WARNING,"logException ",exception);
//        System.out.println("exception ne "+exception);
    }
}
