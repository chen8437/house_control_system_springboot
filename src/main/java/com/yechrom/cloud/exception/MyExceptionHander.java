package com.yechrom.cloud.exception;


import com.yechrom.cloud.dto.vo.response.ExceptionResponseVo;
import com.yechrom.cloud.exception.exceptions.UsernameOrPasswordIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyExceptionHander {


    @ExceptionHandler(value = Exception.class)
    public ExceptionResponseVo deafultExcettionHandle(Exception e){

        ExceptionResponseVo exceptionVo = new ExceptionResponseVo();

        exceptionVo.setErrorcode(0);

        exceptionVo.setError("服务器出现异常,异常原因-"+ e.getMessage() + "-" + e.getClass());

        log.error("输出异常信息~");
        e.printStackTrace();

        return exceptionVo;
    }
}
