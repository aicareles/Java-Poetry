package com.jerry.poetry.authorization.model;

import java.io.Serializable;

/**
* @Description:    token实体类
* @Author:         liulei
* @CreateDate:     2018/7/13 9:34
*/
public class TokenModel implements Serializable {

    //用户id
    private long userId;

    //随机生成的uuid
    private String token;

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public TokenModel() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
