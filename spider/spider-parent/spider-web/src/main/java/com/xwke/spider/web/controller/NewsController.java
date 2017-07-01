package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;

import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.NewsColumnModle;


import com.xwke.spider.web.service.NewsService;

/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class NewsController {

	int pageSize = 10;

	@Resource
	NewsCoumnDao newsCoumnDao;

	@Resource
	NewsService newsService;

	// 查看所有博文
	@RequestMapping(value = "/bitch", method = RequestMethod.GET)
	public String toMain(ModelMap modelMap) {

		return "main";
	}

	@RequestMapping(value = "/news/columnList", method = RequestMethod.GET)
	public String newsColumnList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {

		Page<NewsColumnModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<NewsColumnModle> list = newsCoumnDao.list(new WherePrams(null, null, null));

		modelMap.addAttribute("page", CommonUtil.getPageOnter(pageonter));
		return "news_column_list";
	}

	@RequestMapping(value = "/news/toAddColumn", method = RequestMethod.GET)
	public String toAddNewsColumn(ModelMap modelMap) {

		return "add_column_news";

	}

	@RequestMapping(value = "/news/newsList", method = RequestMethod.GET)
	public String newsList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {
		modelMap.addAttribute("page", newsService.getNewsList(pageNum));
		return "news_list";

	}

}
