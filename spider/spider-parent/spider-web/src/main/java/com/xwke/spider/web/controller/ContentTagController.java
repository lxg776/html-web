package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.quartz.vo.ScheduleJobVo;
import com.xwke.spider.vo.ContentTagVo;
import com.xwke.spider.web.service.ContentTagService;

@Controller
public class ContentTagController {

	@Resource
	ContentTagService service;

	public static String KEY_EDIT = "edit";
	public static String KEY_DEL = "del";

	@RequestMapping(value = "/tag/list", method = RequestMethod.GET)
	public String tagList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {

		// Page<NewsColumnModle> pageonter = PageHelper.startPage(pageNum,
		// pageSize);
		List<ContentTagModle> list = service.getTagList();
		modelMap.addAttribute("dataList", list);
		return "tag/tags_list";
	}

	@RequestMapping(value = "/tag/toSaveTag", method = RequestMethod.GET)
	public String toAddTag(ContentTagVo tagModle, ModelMap modelMap) {

		if (tagModle.getId() != null) {
			ContentTagVo contentTagVo = service.get(tagModle.getId()).getTargetObject(ContentTagVo.class);
			contentTagVo.setKeyWord(tagModle.getKeyWord());
			modelMap.put("tagModle", contentTagVo);
		}

		return "tag/add_tag";

	}

	@RequestMapping(value = "/tag/saveTag", method = RequestMethod.POST)
	public String saveTag(ContentTagVo tagModle) {
		ContentTagModle tag = tagModle.getTargetObject(ContentTagModle.class);

		if (null == tagModle.getId()) {
			service.addTag(tag);
		} else if (KEY_EDIT.equals(tagModle.getKeyWord())) {
			service.updateTag(tag);
		} else if (KEY_DEL.equals(tagModle.getKeyWord())) {
			service.updateTag(tag);
		}

		return "redirect:list";
	}

}
