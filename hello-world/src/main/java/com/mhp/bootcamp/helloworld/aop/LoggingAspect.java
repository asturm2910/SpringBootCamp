package com.mhp.bootcamp.helloworld.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Around("@annotation(PerformanceLogging)")
    public Object logIt(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Finished call in " + (end - start) + "ms");

        return ret;
    }
}
