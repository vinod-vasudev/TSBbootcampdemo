package com.ob.tsb.balances.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private static final String POINTCUT = "execution(* com.ob.tsb.balances.service.*.*(..)) || execution(* com.ob.tsb.balances.client.*.*(..))";
    @Around(POINTCUT)
    @SneakyThrows
    public Object logEndpointPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        if(result instanceof Mono){
            return ((Mono<Object>)result).subscribeOn(Schedulers.parallel()).flatMap(r -> {
                logExecutionTime(className, methodName, stopWatch);
                return Mono.just(r);
            });
        } else if(result instanceof Flux){
            return ((Flux<Object>)result).subscribeOn(Schedulers.parallel()).collectList().flatMapMany(r -> {
                logExecutionTime(className, methodName, stopWatch);
                return Flux.fromIterable(r);
            });
        } else {
            logExecutionTime(className, methodName, stopWatch);
            return result;
        }
    }

    private void logExecutionTime(final String className, final String methodName, final StopWatch stopWatch){
        stopWatch.stop();
        log.info(" [ " + stopWatch.getTotalTimeMillis() + " mls ] lasted execution of " + className + "." + methodName );
    }
}