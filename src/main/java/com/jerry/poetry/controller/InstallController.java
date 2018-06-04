package com.jerry.poetry.controller;

import com.jerry.poetry.base.Result;
import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.domain.Installer;
import com.jerry.poetry.repository.InstallRepository;
import com.jerry.poetry.util.ComUtil;
import com.jerry.poetry.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/app")
public class InstallController {

    private final static Logger logger = LoggerFactory.getLogger(InstallController.class);

    @Autowired
    public InstallRepository appRepository;

    @PostMapping("/uploadInstall")
    public Result<Installer> uploadInstallInfo(@Param("app_package")String app_package, @Param("app_channel")String app_channel,@Param("phone_system")String phone_system,
                                               @Param("phone_brands")String phone_brands, @Param("phone_model")String phone_model,@Param("phone_system_version")String phone_system_version,@Param("run_time")String run_time){
        if(ComUtil.isEmpty(app_package) || ComUtil.isEmpty(app_channel) || ComUtil.isEmpty(phone_system)
                || ComUtil.isEmpty(phone_brands) || ComUtil.isEmpty(phone_model) || ComUtil.isEmpty(phone_system_version) || ComUtil.isEmpty(run_time)){
            return ResultUtils.error(ResultCode.INVALID_PARAM_EMPTY);
        }
        Installer installer = new Installer();
        installer.setApp_package(app_package);
        installer.setApp_channel(app_channel);
        installer.setPhone_system(phone_system);
        installer.setPhone_brands(phone_brands);
        installer.setPhone_system_version(phone_system_version);
        installer.setRun_time(run_time);
        return ResultUtils.ok(appRepository.save(installer));
    }

    @PostMapping("/uploadInstall0")
    public Result<Installer> uploadInstallInfo0(@Valid Installer installer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtils.error(bindingResult.getFieldError().getDefaultMessage());
        }
        String app_package = installer.getApp_package();
        String app_channel = installer.getApp_channel();
        String phone_system = installer.getPhone_system();
        String phone_brands = installer.getPhone_brands();
        String phone_model = installer.getPhone_model();
        String phone_system_version = installer.getPhone_system_version();
        String run_time = installer.getRun_time();
        if(ComUtil.isEmpty(app_package) || ComUtil.isEmpty(app_channel) || ComUtil.isEmpty(phone_system)
                || ComUtil.isEmpty(phone_brands) || ComUtil.isEmpty(phone_model) || ComUtil.isEmpty(phone_system_version) || ComUtil.isEmpty(run_time)){
            return ResultUtils.error(ResultCode.INVALID_PARAM_EMPTY);
        }
        return ResultUtils.ok(appRepository.save(installer));
    }
}
