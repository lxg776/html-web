package com.xwke.spider.web.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xwke.spider.modle.dao.NewsCoumnDao;
import com.xwke.spider.modle.dao.NewsDao;

/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class NewsController {
	
	
	@Resource
	NewsCoumnDao newsCoumnDao;

    // 查看所有博文
    @RequestMapping(value = "/bitch", method = RequestMethod.GET)
    public String toMain(ModelMap modelMap) {
        
        return "main";
    }
    
    @RequestMapping(value = "/news/columnList", method = RequestMethod.GET)
    public String newsColumnList(ModelMap modelMap){
    	
    	
    	
    	
    	return "main_bf";
    
    }
    
    @RequestMapping(value = "/news/toAddColumn", method = RequestMethod.GET)
    public String toAddNewsColumn(ModelMap modelMap){
    	
    	
    	
    	
    	return "add_column_news";
    
    }

}
