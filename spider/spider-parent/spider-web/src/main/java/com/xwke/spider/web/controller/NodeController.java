package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.NodeService;

@Controller
public class NodeController {

	@Resource
	NodeService nodeService;

	@RequestMapping(value = "/node/list", method = RequestMethod.GET)
	public String nodeList(ModelMap modelMap) {

		List<NodeVo> dataList = nodeService.getAllNode();
		modelMap.addAttribute("dataList", dataList);
		
		return "news_list";
	}
}
