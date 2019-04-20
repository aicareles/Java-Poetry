package com.jerry.poetry.config;

import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.exception.ParamJsonException;
import com.jerry.poetry.util.ResultUtils;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//全局铺获异常类
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result resultError(){
        return ResultUtils.error(ResultCode.SERVER_EXCEPTION);
    }

    @ExceptionHandler(ShiroException.class)
    public Result<String> handle403(){
        return ResultUtils.error(ResultCode.NO_PERMITION);
    }

    //在抛出参数异常时  会统一回调该方法
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ParamJsonException.class)
    @ResponseBody
    public Result<String> handleParam(Exception e){
        if(e instanceof ParamJsonException){
            logger.info("参数错误："+e.getMessage());
            return ResultUtils.error(ResultCode.PARAM_ERROR);
        }
        return ResultUtils.error(ResultCode.SERVER_EXCEPTION);
    }
}
