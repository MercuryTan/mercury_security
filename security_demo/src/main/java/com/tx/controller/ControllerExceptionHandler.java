package com.tx.controller;

import com.tx.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 处理UserNotExistException异常
     * @param ue   这个参数的值是： throw new UserNotExistException(id);中的值
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> userNotExistExceptionHandler(UserNotExistException ue){
        Map<String,Object> map = new HashMap<>();
        map.put("id",ue.getId());
        map.put("message",ue.getMessage());
        return map;
    }
}