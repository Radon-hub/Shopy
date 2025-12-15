package org.radon.shopy.shared.aop.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all services in the package
    @Pointcut("execution(* org.radon.shopy.service..*(..))")
    public void serviceLayer() {}

    // Pointcut for all controllers in the package
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {}

    // Pointcut for all repositories in the package
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryLayer() {}


    @Before("serviceLayer()")
    public void before(JoinPoint joinPoint) {
        logger.info("Service Log : Before method Start... | ({}) {}", joinPoint.getSourceLocation().getWithinType().getSimpleName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "serviceLayer()",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result) {
        logger.info("Service Log : After method Returning... | ({}) {} -> {}", joinPoint.getSourceLocation().getWithinType().getSimpleName(), joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "serviceLayer()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        logger.error("Service Log : After method Throwing... | ({}) {} -> {}", joinPoint.getSourceLocation().getWithinType().getSimpleName(), joinPoint.getSignature().getName(), e.toString());
    }

    @Around("serviceLayer()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("Service Log : Around ... | ({}) {} ->  executed in {}ms", pjp.getSourceLocation().getWithinType().getSimpleName(), pjp.getSignature().getName(), duration);
        return result;
    }

}
