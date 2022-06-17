package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.example.db.DSHolder;
import com.example.annotation.DataSource;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DsAspect {
    @Pointcut("@annotation(com.example.annotation.DataSource)")
    public void dataSourcePointcut(){};

    @Around("dataSourcePointcut()")
    public Object selectDataSource(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(DataSource.class);
        try {
            DSHolder.setDS(dataSource.value());
            return point.proceed();
        } finally {
            DSHolder.clear();
        }
    }
}
