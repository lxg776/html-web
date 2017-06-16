package com.xwke.spider.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class NewsController {


    // 查看所有博文
    @RequestMapping(value = "/bitch", method = RequestMethod.GET)
    public String toMain(ModelMap modelMap) {
        
        return "main";
    }

}
