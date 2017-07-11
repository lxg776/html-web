package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xwke.spider.web.service.ImageRecordService;

@Controller
public class ImageRecordController {
	
	
	@Resource
	ImageRecordService imageRecordService;

	@RequestMapping(value = "/record/imagesList", method = RequestMethod.GET)
	public String imagesRecordList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {
		
		modelMap.addAttribute("page", imageRecordService.getList(pageNum));
		return "record/image_record_list";
	}

}
