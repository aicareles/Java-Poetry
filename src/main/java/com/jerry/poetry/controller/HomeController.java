package com.jerry.poetry.controller;

import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.domain.shiro.User;
import com.jerry.poetry.repository.UserRepository;
import com.jerry.poetry.util.ResultUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {
//    @RequestMapping({"/index"})
//    public Result<User> index(){
//        Subject subject = SecurityUtils.getSubject();
//        User principal = (User)subject.getPrincipal();
//        return ResultUtils.ok(principal);
//    }

//    @RequestMapping("/login")
//    public Result<User> login(@RequestParam(value = "username") String username,
//                              @RequestParam(value = "password") String password) throws Exception{
////    public Result<User> login(HttpServletRequest request, Map<String, Object> map) throws Exception{
////    public String login(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("vcode")String vcode, @RequestParam("rememberMe")Boolean rememberMe) throws Exception{
////        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
////        SecurityUtils.getSubject().login(token);
//
////        String username=request.getParameter("username");
////        String password=request.getParameter("password");
//        // 1、获取Subject实例对象
//        Subject currentUser = SecurityUtils.getSubject();
//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//            // 3、将用户名和密码封装到UsernamePasswordToken
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//            // 4、认证
//            try {
//                currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
//                Session session = currentUser.getSession();
//                session.setAttribute("username", username);
//            } catch (AuthenticationException e) {
//                return ResultUtils.error(ResultCode.INVALID_USERNAME_PASSWORD);
//            }
//        }else {
//            return ResultUtils.error(ResultCode.USERNAME_ALREADY_IN);
//        }
//        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
////        String exception = (String) request.getAttribute("shiroLoginFailure");
////        System.out.println("exception=" + exception);
////        String msg = "";
////        if (exception != null) {
////            if (UnknownAccountException.class.getName().equals(exception)) {
////                System.out.println("UnknownAccountException -- > 账号不存在：");
////                msg = "UnknownAccountException -- > 账号不存在：";
////            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
////                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
////                msg = "IncorrectCredentialsException -- > 密码不正确：";
////            } else if ("kaptchaValidateFailed".equals(exception)) {
////                System.out.println("kaptchaValidateFailed -- > 验证码错误");
////                msg = "kaptchaValidateFailed -- > 验证码错误";
////            } else {
////                msg = "else >> "+exception;
////                System.out.println("else -- >" + exception);
////            }
////        }
////        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理
//        return ResultUtils.error(ResultCode.UNAUTHORIZED);
//    }
//
//    @RequestMapping("/logout")
//    public Result<User> logout(){
//        // 1、获取Subject实例对象
//        Subject currentUser = SecurityUtils.getSubject();
//        if(currentUser.isAuthenticated()){
//            currentUser.logout();
//            return ResultUtils.ok(null);
//        }
//        return ResultUtils.error(ResultCode.INVALID_USER);
//    }
//
//    @RequestMapping("/403")
//    public Result<User> unauthorizedRole(){
//        System.out.println("------没有权限-------");
//        return ResultUtils.error(ResultCode.USER_NO_PERMITION);
//    }

    @Autowired
    UserRepository userRepository;
    //导出数据到excel表
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<User> users = userRepository.findAll();

        String fileName = "用户表"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"姓名", "密码", "昵称"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (User user : users) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getName());
            row1.createCell(1).setCellValue(user.getPassword());
            row1.createCell(2).setCellValue(user.getUsername());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}