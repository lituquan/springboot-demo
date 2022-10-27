package com.test.agent.exception;

import com.test.agent.common.ServerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public String handle(Exception e) {
        log.info("e:{}",e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ServerResponse serviceExceptionHandler(ServiceException se) {
        return ServerResponse.error(se.getMsg());
    }
}
