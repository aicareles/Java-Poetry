package com.jerry.poetry.authorization.manager.impl;

import com.jerry.poetry.authorization.manager.TokenManager;
import com.jerry.poetry.authorization.model.TokenModel;
import com.jerry.poetry.config.redis.RedisHelper;
import com.jerry.poetry.constant.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @Description:    通过Redis存储和验证token的实现类
 * @Author:         liulei
 * @CreateDate:     2018/7/13 9:34
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisHelper redis;

    public TokenModel createToken(long userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        //存储到redis并设置过期时间
        redis.valuePut(userId+"", model);
        redis.expirse(userId+"",C.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        TokenModel tokenModel = (TokenModel) redis.getValue(model.getUserId()+"");
        if(tokenModel == null)return false;
        String token = tokenModel.getToken();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.expirse(model.getUserId()+"",C.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken(long userId) {
        redis.remove(userId+"");
    }
}
