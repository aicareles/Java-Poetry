package com.jerry.poetry.controller;

import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.domain.shiro.UserInfo;
import com.jerry.poetry.util.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping({"/index"})
    public Result<UserInfo> index(){
        Subject subject = SecurityUtils.getSubject();
        UserInfo principal = (UserInfo)subject.getPrincipal();
        return ResultUtils.ok(principal);
    }

    @RequestMapping("/login")
    public Result<UserInfo> login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) throws Exception{
//    public Result<UserInfo> login(HttpServletRequest request, Map<String, Object> map) throws Exception{
//    public String login(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("vcode")String vcode, @RequestParam("rememberMe")Boolean rememberMe) throws Exception{
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
//        SecurityUtils.getSubject().login(token);

//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
        // 2、判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {
            // 3、将用户名和密码封装到UsernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            // 4、认证
            try {
                currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
                Session session = currentUser.getSession();
                session.setAttribute("username", username);
            } catch (AuthenticationException e) {
                return ResultUtils.error(ResultCode.INVALID_USERNAME_PASSWORD);
            }
        }else {
            return ResultUtils.error(ResultCode.USERNAME_ALREADY_IN);
        }
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return ResultUtils.error(ResultCode.UNAUTHORIZED);
    }

    @RequestMapping("/logout")
    public Result<UserInfo> logout(){
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.logout();
            return ResultUtils.ok(null);
        }
        return ResultUtils.error(ResultCode.INVALID_USER);
    }

    @RequestMapping("/403")
    public Result<UserInfo> unauthorizedRole(){
        System.out.println("------没有权限-------");
        return ResultUtils.error(ResultCode.USER_NO_PERMITION);
    }

}