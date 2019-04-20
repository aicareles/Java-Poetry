package com.jerry.poetry.constant;

/**
* @Description:    静态常量资源
* @Author:         liulei
* @CreateDate:     2018/5/15 18:03
*/
public class C {

    public interface Cache {
        String POEM_INFO = "poem_info";//诗人信息

    }

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 48;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
}
