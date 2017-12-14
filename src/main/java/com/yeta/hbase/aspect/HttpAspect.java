/*
package com.yeta.hbase.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * Created by Administrator on 2017-11-20.
 *//*

@Aspect
@Configuration
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //设置切点
    @Pointcut("execution(public * com.yeta.hbase.controller.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //在方法执行之前执行

        //获取request和response
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        //设置哪url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @After("log()")
    public void doAfter(){
        //在方法执行之后执行
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object){
        //在最后最后执行
        logger.info("response = {}", object.toString());
    }
}
*/
