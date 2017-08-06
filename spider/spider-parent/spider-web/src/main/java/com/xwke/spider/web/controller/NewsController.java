package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.NewsService;
import com.xwke.spider.web.service.NodeService;

/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class NewsController {

	int pageSize = 10;

	@Resource
	NewsService newsService;

	@Resource
	NodeService nodeService;

	// 查看所有博文
	@RequestMapping(value = "/bitch", method = RequestMethod.GET)
	public String toMain(ModelMap modelMap) {

		return "main";
	}

	@RequestMapping(value = "/news/toAddColumn", method = RequestMethod.GET)
	public String toAddNewsColumn(ModelMap modelMap) {

		return "add_column_news";

	}

	@RequestMapping(value = "/news/newsList", method = RequestMethod.GET)
	public String newsList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "nodeId", defaultValue = "0") String nodeId,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(defaultValue = "0") String[] nodeIdsArray, ModelMap modelMap) {

		long nodeIds[] = null;
		if (!"0".equals(nodeId)) {
			if (nodeId.contains(",")) {
				String[] nodeArray = nodeId.split(",");
				if (null != nodeArray && nodeArray.length > 0) {
					nodeIds = new long[nodeArray.length];
					for (int i = 0; i < nodeArray.length; i++) {
						nodeIds[i] = Long.parseLong(nodeArray[i]);
					}
				}
			} else {
				nodeIds = new long[] { Long.parseLong(nodeId) };
			}
		}

		// 换nodeIdsArray
		if (nodeIds == null) {
			if (null != nodeIdsArray && nodeIdsArray.length > 0) {
				nodeIds = new long[nodeIdsArray.length];
				for (int i = 0; i < nodeIdsArray.length; i++) {
					nodeIds[i] = Long.parseLong(nodeIdsArray[i]);
				}
			}
		}

		List<NodeVo> nodeList = nodeService.getNodeList();
		if (null != nodeIds && nodeIds.length > 0) {
			for (int i = 0; i < nodeIds.length; i++) {
				long itemNodeId = nodeIds[i];
				for (NodeVo item : nodeList) {
					if (item.getId() == itemNodeId) {
						item.setSelectValue(1);
					}
				}
			}
		}
		modelMap.put("nodeList", nodeList);
		modelMap.put("nodeId", nodeId);
		modelMap.put("keyWord", keyWord);
		modelMap.addAttribute("page", newsService.getNewsList(pageNum, nodeIds, keyWord));

		return "news/news_list";

	}

}
