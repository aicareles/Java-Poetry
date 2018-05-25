package com.jerry.girl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* @Description:    首次进入应用上传安装信息
* @Author:         liulei
* @CreateDate:     2018/5/15 10:34
*/
@Entity
public class Installer {

    @Id
    @GeneratedValue
    private Integer id;

    //包名
    @NotNull(message = "包名不能为空")
    private String app_package;

    //渠道名
    private String app_channel;

    //手机系统（android/iOS）
    private String phone_system;

    //手机版本号
    private String phone_brands;

    //手机品牌
    private String phone_model;

    //手机系统版本
    private String phone_system_version;

    //安装运行时间(格式：2018-05-15:10:39:54)
    private String run_time;

    public Installer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp_package() {
        return app_package;
    }

    public void setApp_package(String app_package) {
        this.app_package = app_package;
    }

    public String getApp_channel() {
        return app_channel;
    }

    public void setApp_channel(String app_channel) {
        this.app_channel = app_channel;
    }

    public String getPhone_system() {
        return phone_system;
    }

    public void setPhone_system(String phone_system) {
        this.phone_system = phone_system;
    }

    public String getPhone_brands() {
        return phone_brands;
    }

    public void setPhone_brands(String phone_brands) {
        this.phone_brands = phone_brands;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public String getPhone_system_version() {
        return phone_system_version;
    }

    public void setPhone_system_version(String phone_system_version) {
        this.phone_system_version = phone_system_version;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }
}
