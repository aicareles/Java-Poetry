//package com.jerry.poetry.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//@ConfigurationProperties(prefix = "mail")
//public class MailConfiguration {
//
//    private String protocol;
//    private String host;
//    private int port;
//    private boolean smtpAuth;
//    private boolean smtpStarttlsEnable;
//    private String username;
//    private String password;
//    private String smtpSslTrust;
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.smtp.auth", smtpAuth);
//        mailProperties.put("mail.smtp.starttls.enable", smtpStarttlsEnable);
//        mailProperties.put("mail.smtp.ssl.trust", smtpSslTrust);
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//        mailSender.setProtocol(protocol);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        return mailSender;
//    }
//
//    public void setProtocol(String protocol) {
//        this.protocol = protocol;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public void setSmtpAuth(boolean smtpAuth) {
//        this.smtpAuth = smtpAuth;
//    }
//
//    public void setSmtpStarttlsEnable(boolean smtpStarttlsEnable) {
//        this.smtpStarttlsEnable = smtpStarttlsEnable;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setSmtpSslTrust(String smtpSslTrust) {
//        this.smtpSslTrust = smtpSslTrust;
//    }
//}
