package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.NewsColumnModle;

import c_pagination.PageOnterModle;

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
	public String newsColumnList(ModelMap modelMap) {

		Page<NewsColumnModle> pageonter = PageHelper.startPage(6, 10);
		List<NewsColumnModle> list = newsCoumnDao.list(new WherePrams(null, null, null));
		PageOnterModle page = CommonUtil.getPageOnter(pageonter);

		modelMap.addAttribute("page", CommonUtil.getPageOnter(pageonter));

		return "main_bf";
	}

	@RequestMapping(value = "/news/toAddColumn", method = RequestMethod.GET)
	public String toAddNewsColumn(ModelMap modelMap) {

		return "add_column_news";

	}

}
