package cn.lmz.mike.web.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerLogAspect {

    @Pointcut("execution(* cn.lmz.mike.*.controller.*.*(..))")
    public void controllerLogPoint(){}

    @Before(value = "controllerLogPoint()")
    public void beforeLog(JoinPoint joinPoint){
        Class clazz = joinPoint.getTarget().getClass();
        String method = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(">>>Request Start: {}, 请求参数: {}", method, JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "controllerLogPoint()", returning = "response")
    public void afterReturnLog(JoinPoint joinPoint, Object response){
        Class clazz = joinPoint.getTarget().getClass();
        String method = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(">>>Request End: {}, 响应结果: {}", method, JSON.toJSONString(response));
    }

    @AfterThrowing(value = "controllerLogPoint()", throwing= "e")
    public void afterThrowLog(JoinPoint joinPoint, Throwable e){
        Class clazz = joinPoint.getTarget().getClass();
        String method = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(">>>Request Invoke Error: {}, Message: {}", method, JSON.toJSONString(e.getMessage()));
    }

}
