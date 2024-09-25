package com.example.springboot.mvcdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoginAspect {
    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());
    //setup pointcut declarations
    @Pointcut("execution(* com.example.springboot.mvcdemo.controller.*.*(..))")
    private void forControllerPackage() {}
    //do the same for service and dao
    @Pointcut("execution(* com.example.springboot.mvcdemo.service.*.*(..))")
    private void forServicePackage() {}
    @Pointcut("execution(* com.example.springboot.mvcdemo.dao.*.*(..))")
    private void forDaoPackage() {}

   @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
   private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method : " + theMethod);
        //display the arguments to the method
        //get the arguments
        Object[] args = theJoinPoint.getArgs();
        //loop through and display args
        for(Object tempArg : args) {
            myLogger.info("======> argument: " + tempArg);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult" )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: calling method : " + theMethod);

        myLogger.info("======> Result: " + theResult);

    }
}
