package cn.lmz.mike.web.handler;

import cn.lmz.mike.common.code.ErrorCode;
import cn.lmz.mike.common.exception.ErrorCodeException;
import cn.lmz.mike.common.web.response.JsonRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = {"cn.lmz"})
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonRes handlerException(Exception e){
        log.error("未知异常", e);
        return JsonRes.instance(ErrorCode.SYSTEM_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorCodeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonRes handlerErrorCodeException(ErrorCodeException e){
        log.error("运行异常", e);
        return JsonRes.instance(e);
    }

}
