package com.tx.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.tx.web.controller.UserController.*(..))")
    public Object handlerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        for (Object a : args) {
            System.out.println("args  is :" + a);
        }
        System.out.println("--- aspect invoke begin --- :");

        Object o = joinPoint.proceed(); //执行被拦截的方法

        long endTime = System.currentTimeMillis();
        System.out.println("--- aspect invoke end --- 耗时：" + (endTime - beginTime));
        return o;
    }
}