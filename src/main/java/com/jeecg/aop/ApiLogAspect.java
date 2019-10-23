package com.jeecg.aop;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

@Component
@Aspect
public class ApiLogAspect {

    private static String requestLog;
    private static String responseLog;
    static {
        requestLog = new StringBuilder("request_%s %n")
                .append("IP:%s %n")
                .append("URL:%s %n")
                .append("controller:%s %n")
                .append("请求参数:%s %n").toString();
        responseLog = new StringBuilder("response_%s %n")
                .append("返回结果:%s %n").toString();
    }
    private static final Logger logger = Logger.getLogger("API");

    @Pointcut("execution(* com.jeecg.controller.*.*.apiList(..)))|| execution(* com.jeecg.controller.*.*.apiSave(..)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        UUID uuid = UUID.randomUUID();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> className = Class.forName(classType);
        //IP地址
        String url = request.getRequestURL().toString();
        String ipAddr = getRemoteHost(request);
        String reqParam = preHandle(joinPoint,request);
        logger.info(String.format(requestLog,uuid.toString(),className,ipAddr,url,reqParam));
        Object result= joinPoint.proceed();
        String respParam = postHandle(result);
        logger.info(String.format(responseLog,uuid.toString(),respParam));
        return result;
    }

    /**
     * 入参数据
     * @param joinPoint
     * @param request
     * @return
     */
    private String preHandle(ProceedingJoinPoint joinPoint,HttpServletRequest request) {

        String reqParam = "";
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        for (Annotation annotation : annotations) {
            //此处可以改成自定义的注解
            if (annotation.annotationType().equals(RequestMapping.class)) {
                reqParam = JSON.toJSONString(request.getParameterMap());
                break;
            }
        }
        return reqParam;
    }

    /**
     * 返回数据
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if(null == retVal){
            return "";
        }
        return JSON.toJSONString(retVal);
    }


    /**
     * 获取目标主机的ip
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
