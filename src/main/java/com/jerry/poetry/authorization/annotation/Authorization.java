package com.jerry.poetry.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description:    在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
* @Author:         liulei
* @CreateDate:     2018/7/13 9:38
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
