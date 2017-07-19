package com.xwke.spider.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.util.StringUtil;
import com.xwke.spider.vo.DataOperationVo;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;

/**
 * Created by liangxg on 2016/3/18.
 */
@Controller
public class ExecutorController {

	@Resource
	ExecutorService executorService;

	@RequestMapping(value = "/executor/list", method = RequestMethod.GET)
	public String executorList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {

		modelMap.addAttribute("page", executorService.getList(pageNum));
		return "executor/executor_list";
	}

	@RequestMapping(value = "/executor/toSave", method = RequestMethod.GET)
	public String toAddExecutor(ExectorVo vo, ModelMap modelMap) {

		if (vo != null && vo.getId() != 0) {
			ExectorVo returnVo = executorService.getExecutorById(vo.getId());
			returnVo.setKeyWord(vo.getKeyWord());
			modelMap.addAttribute("vo", returnVo);
		}

		return "executor/add_executor";

	}

	@RequestMapping(value = "/executor/toEditOperation", method = RequestMethod.GET)
	public String toEditOperation(ExectorVo vo, ModelMap modelMap) {

		ExectorVo returnVo = executorService.getExecutorAndDataOperationById(vo.getId());
		
		modelMap.addAttribute("vo", returnVo);
		
		return "executor/edit_rule";

	}

	@RequestMapping(value = "/executor/save", method = RequestMethod.GET)
	public String addExecutor(ExectorVo vo, ModelMap modelMap) {

		if ("edit".equals(vo.getKeyWord())) {
			executorService.updateExecutor(vo);
		} else {
			executorService.addExecutor(vo);
		}

		return "redirect:list";
	}

	@RequestMapping(value = "/executor/toOperationEdit", method = RequestMethod.GET)
	public String toOperationEdit(DataOperationVo vo, ModelMap modelMap) {
		DataOperationVo returnVo = null;
		if (vo.getId() != null) {
			returnVo = executorService.getDataOperationById(vo.getId());

		}

		if (returnVo == null) {
			returnVo = vo;
		}
		returnVo.setKeyWord(vo.getKeyWord());
		returnVo.setExecutorId(vo.getExecutorId());
		if (!StringUtil.isEmpty(returnVo.getFileName())) {
			String editFile = returnVo.getEditFile();
			if (DataOperationVo.FILE_NEWSIMGS.equals(editFile)) {
				returnVo.setEditFileShow("新闻图片");
			}
		}
		modelMap.addAttribute("vo", returnVo);
		return "executor/edit_item_operation";
	}

	@RequestMapping(value = "/executor/saveOperation", method = RequestMethod.POST)
	public String saveOperation(DataOperationVo vo) {
		if ("edit".equals(vo.getKeyWord())) {
			executorService.updateDataOperation(vo);
		} else {
			executorService.addDataOperation(vo);
		}
		// 返回编辑列表
		return "redirect:toEditOperation?id=" + vo.getExecutorId();
	}

}
