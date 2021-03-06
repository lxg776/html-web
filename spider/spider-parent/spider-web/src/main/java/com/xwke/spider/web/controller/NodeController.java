package com.xwke.spider.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.ContentTagService;
import com.xwke.spider.web.service.NodeService;

@Controller
public class NodeController {

	@Resource
	NodeService nodeService;
	@Resource
	ContentTagService tagService;

	/*
	 * 节点列表
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/node/list", method = RequestMethod.GET)
	public String nodeList(ModelMap modelMap) {

		List<NodeVo> dataList = nodeService.getAllNode();
		String jsonStr = JSONArray.toJSONString(dataList);
		modelMap.addAttribute("jsonStr", jsonStr);

		return "node/node_list";
	}

	/**
	 * 去添加节点
	 * 
	 * @param fid
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/node/toAdd", method = RequestMethod.GET)
	public String toAddNode(@RequestParam(name = "fid", defaultValue = "0") long fid, ModelMap modelMap) {
		NodeVo fatherNode = nodeService.getNodeByid(fid);
		if (null != fatherNode) {
			modelMap.put("fatherNode", fatherNode);
		}

		return "node/add_node";
	}

	/**
	 * 增加节点
	 * 
	 * @param fid
	 * @param nodeName
	 * @param sort
	 * @param level
	 * @return
	 */
	@RequestMapping(value = "/node/add", method = RequestMethod.POST)
	public String AddNode(@RequestParam(name = "fid", defaultValue = "0") long fid,
			@RequestParam(name = "node_name", defaultValue = "0") String nodeName,
			@RequestParam(name = "sort", defaultValue = "0") int sort,
			@RequestParam(name = "level", defaultValue = "0") int level) {

		NodeVo vo = new NodeVo();
		vo.setNodeName(nodeName);
		vo.setSort(sort);

		if (fid == 0) {
			nodeService.addRootNode(vo);
		} else {
			List<NodeModle> childList = new ArrayList<>();
			NodeModle modle = new NodeModle();
			modle.setNodeName(nodeName);
			modle.setSort(sort);
			childList.add(modle);
			nodeService.addChildNode(fid, level + 1, childList);
		}
		// nodeService.addChildNode(fid, node_level, childList);
		return "redirect:list";
	}

	@RequestMapping(value = "/node/toEdit", method = RequestMethod.GET)
	public String toEditNode(@RequestParam(name = "nodeId", defaultValue = "0") long nodeId, ModelMap modelMap) {
		NodeVo node = nodeService.getNodeByid(nodeId);
		List<ContentTagModle> tagList = tagService.getTagListByNodeId(nodeId);
		modelMap.addAttribute("node", node);
		modelMap.addAttribute("tagList", tagList);
		return "node/edit_node";
	}

	@RequestMapping(value = "/node/toAddTag", method = RequestMethod.GET)
	public String toAddTag(@RequestParam(name = "nodeId", defaultValue = "0") long nodeId, ModelMap modelMap) {

		NodeVo node = nodeService.getNodeByid(nodeId);
		List<ContentTagModle> tagList = tagService.getUnAddTagListByNodeId(nodeId);

		modelMap.addAttribute("tagList", tagList);
		modelMap.addAttribute("node", node);
		return "node/edit_addTag";
	}

	@RequestMapping(value = "/node/addTag", method = RequestMethod.POST)
	public String addTag(@RequestParam(name = "tagIds") Long[] tagIds, @RequestParam Long nodeId) {
		if (tagIds != null) {
			for (int i = 0; i < tagIds.length; i++) {
				nodeService.addTag(tagIds[i], nodeId);
			}
		}
		return "redirect:toEdit?nodeId=" + nodeId;
	}
	
	@RequestMapping(value = "/node/delTag", method = RequestMethod.GET)
	public String delTag(@RequestParam Long nodeId,@RequestParam Long tagId) {
		
		tagService.delTagnodeRel(nodeId, tagId);
		
		return "redirect:toEdit?nodeId=" + nodeId;
	}

	/**
	 * 更新节点
	 * 
	 * @param id
	 * @param nodeName
	 * @param sort
	 * @return
	 */
	@RequestMapping(value = "/node/updateNode", method = RequestMethod.POST)
	public String updateNode(@RequestParam(name = "id", defaultValue = "0") long id,
			@RequestParam(name = "node_name", defaultValue = "") String nodeName,
			@RequestParam(name = "sort", defaultValue = "0") int sort) {

		NodeModle modle = new NodeModle();
		modle.setId(id);
		modle.setNodeName(nodeName);
		modle.setSort(sort);
		nodeService.updateNode(modle);

		return "redirect:list";
	}

}
