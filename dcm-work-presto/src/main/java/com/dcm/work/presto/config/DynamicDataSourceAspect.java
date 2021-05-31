package com.dcm.work.presto.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Slf4j
@Component
public class DynamicDataSourceAspect {

    /**
     * 拦截有TargetDataSource注解的方法
     * @param joinPoint
     * @param dataSource
     */
    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource dataSource) {
        String dsId = dataSource.name();
        // 如果存在指定的数据源，则放入ThreadLocal中
        if (DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            log.error("正在使用{}数据源,->{}", dsId, joinPoint.getSignature());
            // 需要引入lombok插件
            DynamicDataSourceContextHolder.setDataSource(dsId);
        } else {
            log.info("{}不存在,使用默认数据源,->{}", dsId, joinPoint.getSignature());
        }
    }

    /**
     * 释放数据库链接资源
     * @param joinPoint
     * @param dataSource
     */
    @After(value = "@annotation(dataSource)")
    public void releaseLocal(JoinPoint joinPoint, TargetDataSource dataSource) {
        if (DynamicDataSourceContextHolder.getDataSource() != null) {
            DynamicDataSourceContextHolder.getContextHolder().remove();
            log.info("释放数据源：" + dataSource.name() + "的ThreadLocal绑定！！！");
        }
    }
}
