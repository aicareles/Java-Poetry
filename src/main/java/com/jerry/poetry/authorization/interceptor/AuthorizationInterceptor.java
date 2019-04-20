package com.jerry.poetry.authorization.interceptor;

import com.alibaba.fastjson.JSON;
import com.jerry.poetry.authorization.annotation.Authorization;
import com.jerry.poetry.authorization.manager.TokenManager;
import com.jerry.poetry.authorization.model.TokenModel;
import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.constant.C;
import com.jerry.poetry.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description: 自定义拦截器，判断此次请求是否有权限
 * @Author: liulei
 * @CreateDate: 2018/7/13 9:37
 */
@Controller
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(C.AUTHORIZATION);
        //验证token
        TokenModel model = manager.getToken(authorization);
        if (manager.checkToken(model)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(C.CURRENT_USER_ID, model.getUserId());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            responseResult(response, ResultUtils.error(ResultCode.UNAUTHORIZED));
            return false;
        }
        return true;
    }

    //输出信息到前端或客户端
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
