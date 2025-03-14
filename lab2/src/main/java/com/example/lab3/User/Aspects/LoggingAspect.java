package com.example.lab3.User.Aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.lab3.User.*.*(..))")
    public void logBeforeMethodExecution() {
        System.out.println("Executing method in User package...");
    }
}
