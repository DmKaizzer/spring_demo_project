package com.example.demo.aspects;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllersAspect {
    private static Logger logger = LogManager.getLogger();

    @Pointcut("within(com.example.demo.controllers..*)")
    public void callAtMyServicePublic() { }

    @Around("callAtMyServicePublic()")
    public Object afterCallAt(ProceedingJoinPoint jp) {
        try {
            logger.info(jp.toString());
            return jp.proceed();
        } catch (Throwable e) {
            String methodName = jp.getSignature().getName();
            logger.error("Exception " + methodName + ":\n" + e.getMessage());
            return "error";
        } finally {
            logger.info("Controller end work " + jp.toString());
        }
    }
}
