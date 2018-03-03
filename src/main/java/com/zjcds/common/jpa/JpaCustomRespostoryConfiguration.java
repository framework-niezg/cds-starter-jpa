package com.zjcds.common.jpa;

import com.zjcds.common.jpa.impl.CustomRepostoryImpl;
import com.zjcds.common.jpa.utils.NearestEntityGraphUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.reflect.Method;

/**
 * created date：2017-09-07
 * @author niezhegang
 */

@EnableAspectJAutoProxy
@Aspect
@Configuration
@EnableJpaRepositories(basePackages={"com.zjcds"},repositoryBaseClass= CustomRepostoryImpl.class)
@EntityScan("com.zjcds")
public class JpaCustomRespostoryConfiguration {

    @Pointcut("execution(* com.zjcds..*(..)) && @annotation(com.zjcds.common.jpa.annotation.NearestEntityGraph)")
    public void customRespostory() {}

    @Around("customRespostory()")
    public Object doJsonViewProcess(ProceedingJoinPoint pjp) throws Throwable{
        Method oldMethod = NearestEntityGraphUtils.getCurrentInvocationMethod();
        try {
            if (pjp instanceof MethodInvocationProceedingJoinPoint) {
                MethodInvocationProceedingJoinPoint mipjp = (MethodInvocationProceedingJoinPoint) pjp;
                if (mipjp.getSignature() instanceof MethodSignature) {
                    Method sourceMethod = ((MethodSignature) mipjp.getSignature()).getMethod();
                    NearestEntityGraphUtils.setCurrentInvocationMethod(sourceMethod);
                }
            }
            return pjp.proceed();
        }
        finally {
            NearestEntityGraphUtils.setCurrentInvocationMethod(oldMethod);
        }

    }
}
