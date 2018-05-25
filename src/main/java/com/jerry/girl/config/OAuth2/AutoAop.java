package com.jerry.girl.config.OAuth2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Aspect
public class AutoAop
{

    @Around("@annotation(requestMapping)")
    public Object authAround(ProceedingJoinPoint joinPoint, RequestMapping requestMapping) throws Throwable
    {
        Object result = joinPoint.proceed();
        System.out.println(result);
        return result;
    }


    @AfterReturning(pointcut = "execution(* org.springframework.security.oauth2.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterReturning advise");
    }
}
