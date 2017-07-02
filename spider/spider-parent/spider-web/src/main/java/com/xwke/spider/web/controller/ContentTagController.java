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
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.quartz.vo.ScheduleJobVo;
import com.xwke.spider.web.service.ContentTagService;

@Controller
public class ContentTagController {

	@Resource
	ContentTagService service;

	@RequestMapping(value = "/tag/list", method = RequestMethod.GET)
	public String tagList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {

		// Page<NewsColumnModle> pageonter = PageHelper.startPage(pageNum,
		// pageSize);
		List<ContentTagModle> list = service.getTagList();
		modelMap.addAttribute("dataList", list);
		return "tag/tags_list";
	}

	@RequestMapping(value = "/tag/toAddTag", method = RequestMethod.GET)
	public String toAddTag(ContentTagModle tagModle, ModelMap modelMap) {

		if (tagModle.getId() != null) {
			ContentTagModle tag = service.get(tagModle.getId());
			// scheduleJob.setKeywords(scheduleJobVo.getKeywords());
			modelMap.put("tagModle", tag);
		}

		return "tag/add_tag";

	}

	@RequestMapping(value = "/tag/saveTag", method = RequestMethod.GET)
	public String saveTag(ContentTagModle tagModle) {
		if (tagModle.getId() != 0) {

		}

		return "tag/add_tag";
	}

}
