package com.example.demo.Aspects;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.demo.controllers.Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🔵 Starting execution of: " + joinPoint.getSignature().getName());
    }
}
