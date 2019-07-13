package com.airtel.loggingaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLogger {

    private static Logger logger = LoggerFactory.getLogger(MethodLogger.class);

    @Around("execution(public * com.airtel.loggingaspect.Method.*(..)) && !@annotation(Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info(
                "Method called - {}({}) Its result - {} Time to execute : {} ",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );
        for(Object arg:point.getArgs()){
            if(arg.getClass()==Data.class){
                logger.info("GAME OVER. YOU WIN, TIME TO GO HOME.");
            }
        }
        return result;
    }

    @Pointcut("execution(public * com.airtel.loggingaspect.Data.get*())")
    public void getterPointCut(){}

    @Before("getterPointCut()")
    public void loggingAdvice(JoinPoint joinPoint){
        logger.info("Following getter method called - {}",joinPoint.getSignature().getName());
    }
    @After("getterPointCut()")
    public void secondAdvice(JoinPoint joinPoint){
        logger.info("After getter {}",joinPoint.getSignature().getName());
    }

    @Around("execution(public * com.airtel.loggingaspect.Method.*(..)) && @annotation(Loggable)")
    public Object annotaion(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info("annotation based segregation took on following method - {}",point.getSignature().getName());
        return result;
    }

}
