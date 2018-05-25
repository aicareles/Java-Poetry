package com.jerry.girl.util;

import com.jerry.girl.base.Result;
import com.jerry.girl.base.ResultCode;

public class ResultUtils {

    //查询成功
    public static Result ok(Object object){
        Result result = new Result(object);
        return result;
    }

    //查询失败
    public static Result error(ResultCode resultCode){
        Result result = new Result(resultCode.getCode(), resultCode.getMsg());
        return result;
    }

    //查询失败  自定义错误信息
    public static Result error(String msg){
        Result result = new Result(-1, msg);
        return result;
    }
}
