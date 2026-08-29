package org.example.todoapi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {
    @Around("@annotation(TimeMonitor)")
    public  void  logTime (ProceedingJoinPoint joinPoint){
//        System.out.println("Logging Time ");
        try {
         joinPoint.proceed();
        }catch (Throwable e){
            System.out.println("SomeThing Wrong ");
        }
        finally {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            System.out.println("time :"+ (end-start));
        }

    }

}
