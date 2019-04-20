package com.jerry.poetry.controller;

import com.jerry.poetry.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {



//    @Qualifier("javaMailSender")
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailUtils mailUtil;

    /**
     * 发送邮件
     *
     * @throws Exception
     */
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public void sendSimpleMail() throws Exception {
//        user.setEmail("adobe1874@126.com");
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(user.getEmail());
        message.setTo("2903365259@qq.com");
        message.setSubject("用户登录提醒");
        message.setText("你的到来让我很高兴,谢谢");
        mailUtil.sendSimpleMail(javaMailSender, message);
//        mailUtil.sendTemplateMail(javaMailSender, "2903365259@qq.com", "艾神一不小心");
    }
}
