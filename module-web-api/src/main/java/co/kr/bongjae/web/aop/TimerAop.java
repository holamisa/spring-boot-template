package co.kr.bongjae.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @Timer 어노테이션이 붙은 메소드의 실행 시간을 측정하는 AOP
 */
@Component
@Slf4j
@Aspect
public class TimerAop {

    @Pointcut("@annotation(co.kr.bongjae.web.common.annotation.Timer)")
    public void timerPointCut(){}

    @Around(value = "timerPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        var stopWatch = new StopWatch();
        stopWatch.start();

        var object = joinPoint.proceed();

        stopWatch.stop();

        log.info("aop around - {} 실행 이후 - 총 시간(ms) : {}", method.getName(), stopWatch.getTotalTimeMillis());

        return object;
    }
}