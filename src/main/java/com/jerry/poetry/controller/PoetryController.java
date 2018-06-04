package com.jerry.poetry.controller;

import com.jerry.poetry.base.ResultCode;
import com.jerry.poetry.util.ResultUtils;
import com.jerry.poetry.domain.Poetry;
import com.jerry.poetry.repository.PoetryRepository;
import com.jerry.poetry.base.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/poetrys")
public class PoetryController {

    private final static Logger logger = LoggerFactory.getLogger(PoetryController.class);
    @Autowired
    private PoetryRepository poetryRepository;

    //简体转繁体
//    @PostMapping(value = "PoetryConverter")
//    public Result<Poetry> zhPoetryConverter(){
//        ZHConverter converter2 = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
//        Poetry s = null;
//        for(int i = 311829; i < 332858; i++){
//            Poetry poetry = poetryRepository.findById(i).get();
//            poetry.setTitle(converter2.convert(poetry.getTitle()));
//            poetry.setContent(converter2.convert(poetry.getContent()));
//            poetry.setAuthor(converter2.convert(poetry.getAuthor()));
//            s = poetryRepository.saveAndFlush(poetry);
//        }
//        return ResultUtils.ok(s);
//    }

    //随机获取推荐的一篇文章
    @PostMapping(value = "/randomOnePoetry")
    @RequiresPermissions("poetrys:randomOnePoetry")
    public Result<Poetry> randomOnePoetry(){
        int counts = (int) poetryRepository.count();
        int id = new Random().nextInt(counts);
        return ResultUtils.ok(poetryRepository.findById(id));
    }

    //随机获取推荐的十篇文章
    @PostMapping(value = "/randomTenPoetry")
    @RequiresPermissions("poetrys:randomTenPoetry")
    public Result<Poetry> randomTenPoetry(){
        int counts = (int) poetryRepository.count();
        List<Integer> list  = new ArrayList<>();
        int id;
        while (list.size() < 10){
            id = new Random().nextInt(counts);
            if(!list.contains(id)){
                list.add(id);
            }
        }
        return ResultUtils.ok(poetryRepository.findAllById(list));
    }

    //通过诗人名称、诗标题、诗内容等  获该相关信息
    @Cacheable(value="searchPoetry")
    @PostMapping(value = "/searchPoetry")
    @RequiresPermissions("poetrys:searchPoetry")
    public Result<Poetry> searchResult(@Param("page")Integer page, @Param("keyword")String keyword){
        if(StringUtils.isEmpty(keyword) || StringUtils.isEmpty(page)){
            return ResultUtils.error(ResultCode.INVALID_PARAM_EMPTY);
//            //上面和下面的两种处理都可以
//            throw new ParamJsonException("参数不能为空");
        }
//        List<Sort.Order> orders = new ArrayList<>();
//        orders.add(new Sort.Order(Sort.Direction.ASC,"author"));
//        orders.add(new Sort.Order(Sort.Direction.DESC,"title"));
//        orders.add(new Sort.Order(Sort.Direction.DESC,"content"));
//        Page<Poetry> page1 = poetryRepository.findAllByKey(keyword, PageRequest.of(page,10,Sort.by(orders)));

        //先查询作者的关键字
        Page<Poetry> pages = poetryRepository.findByAuthorContaining(keyword, PageRequest.of(page,10));
        //记录当前总页数
        int totalpages = pages.getTotalPages();
        if(pages.getContent().size() == 0){
            //查询标题中含有的关键字
            int tempPage = page - pages.getTotalPages();
            pages = poetryRepository.findByTitleContaining(keyword, PageRequest.of(tempPage,10));
            totalpages += pages.getTotalPages();
            if(pages.getContent().size() == 0){
                //查询内容中含有的关键字
                tempPage = page - totalpages;
                pages = poetryRepository.findByContentContaining(keyword, PageRequest.of(tempPage,10));
            }
        }
        return ResultUtils.ok(pages.getContent());
    }

}
