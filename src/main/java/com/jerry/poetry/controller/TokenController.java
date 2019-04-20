package com.jerry.poetry.controller;

import com.jerry.poetry.authorization.annotation.CurrentUser;
import com.jerry.poetry.authorization.manager.TokenManager;
import com.jerry.poetry.authorization.model.TokenModel;
import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.domain.shiro.User;
import com.jerry.poetry.repository.UserRepository;
import com.jerry.poetry.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
* @Description:    获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
* @Author:         liulei
* @CreateDate:     2018/7/13 9:43
*/
@RestController
@RequestMapping("/poetrys")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(value = "/register")
    public Result<User> register(@RequestParam(value = "username", required = true) String username,
                                 @RequestParam(value = "password", required = true) String password) {

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return ResultUtils.error(ResultCode.INVALID_PARAM_EMPTY);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return ResultUtils.ok(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return ResultUtils.error(ResultCode.INVALID_USERNAME_PASSWORD);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getUid());
        user.setToken(model.getToken());
        return ResultUtils.ok(user);
    }

//    @PostMapping("login")
//    public Result<User> loginTest(@RequestParam String username, @RequestParam String password){
//        JWTUtil.createToken(username);
//    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result<User> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getUid());
        return ResultUtils.ok("登出成功!");
    }

}
