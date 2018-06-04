package com.jerry.poetry.controller;

import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.util.ResultUtils;
import com.jerry.poetry.domain.Author;
import com.jerry.poetry.repository.AuthorRepository;
import com.jerry.poetry.base.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/poem")
public class AuthorController {

    private final static Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Cacheable(value="poemInfo")
    @PostMapping(value = "/poemInfo")
    @RequiresPermissions("poem:poemInfo")
    public Result<Author> author(@RequestParam("author_id") int author_id, @RequestParam("author_name")String author_name) {
        if(StringUtils.isEmpty(author_id) || StringUtils.isEmpty(author_name)){
            return ResultUtils.error(ResultCode.INVALID_PARAM_EMPTY);
        }
        Author author;
        Optional<Author> optional = authorRepository.getAuthorByIdAndName(author_id, author_name);
        if (optional.isPresent()) {
            author = optional.get();
            //通过\n或者多个空格 进行过滤去重
            if (!StringUtils.isEmpty(author.getIntro_l())) {
                String s = author.getIntro_l();
                String intro = s.split("\\s +")[0];
                author.setIntro_l(intro);
            }
        } else {
           return ResultUtils.error(ResultCode.NO_FIND_THINGS);
        }
        return ResultUtils.ok(author);
    }

    //简体转繁体
//    @PostMapping(value = "converter")
//    public Result<Author> zhAuthorConverter(){
//        ZHConverter converter2 = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
//        Author s = null;
//        for(int i = 14701; i < 16265; i++){
//            Author author = authorRepository.findById(i).get();
//            author.setName(converter2.convert(author.getName()));
//            author.setIntro_l(converter2.convert(author.getIntro_l()));
//            author.setIntro_s(converter2.convert(author.getIntro_s()));
//            s = authorRepository.saveAndFlush(author);
//        }
//        return ResultUtils.ok(s);
//    }
}
