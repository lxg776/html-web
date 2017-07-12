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
import com.xwke.spider.dao.ExecutorDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.ExecutorModle;


/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class ExecutorController {

	int pageSize = 10;

	@Resource
	ExecutorDao executorDao;

	


	@RequestMapping(value = "/executor/list", method = RequestMethod.GET)
	public String executorList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {

		Page<ExecutorModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<ExecutorModle> list = executorDao.list(new WherePrams(null, null, null));
		modelMap.addAttribute("page", CommonUtil.getPageOnter(pageonter));
		return "executor/executor_list";
	}

	@RequestMapping(value = "/executor/toAdd", method = RequestMethod.GET)
	public String toAddExecutor(ModelMap modelMap) {

		return "executor/add_executor";

	}

	
}
