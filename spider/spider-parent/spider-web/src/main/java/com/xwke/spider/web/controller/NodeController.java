package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.NodeService;

@Controller
public class NodeController {

	@Resource
	NodeService nodeService;

	@RequestMapping(value = "/node/list", method = RequestMethod.GET)
	public String nodeList(ModelMap modelMap) {

		List<NodeVo> dataList = nodeService.getAllNode();
		String jsonStr = JSONArray.toJSONString(dataList);
		modelMap.addAttribute("jsonStr", jsonStr);

		return "node/node_list";
	}

	@RequestMapping(value = "/node/toAdd", method = RequestMethod.GET)
	public String toAddNode(@RequestParam(name = "fid", defaultValue = "0") long fid, ModelMap modelMap) {
		NodeVo fatherNode = nodeService.getNodeByid(fid);
		if (null != fatherNode) {
			modelMap.put("fatherNode", fatherNode);
		}

		return "node/add_node";
	}

	@RequestMapping(value = "/node/add", method = RequestMethod.GET)
	public String AddNode(@RequestParam(name = "fid", defaultValue = "0") long fid,
			@RequestParam(name = "node_name", defaultValue = "0") String nodeName,@RequestParam(name = "level", defaultValue = "0") int level) {
		

		
		//nodeService.addChildNode(fid, node_level, childList);
		

		return "redirect:list";
	}

}
