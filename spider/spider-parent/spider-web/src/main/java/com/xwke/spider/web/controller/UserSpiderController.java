package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.util.StringUtil;
import com.xwke.security.util.SpringSecurityUtils;
import com.xwke.spider.huntsman.util.RandomUtils;
import com.xwke.spider.modle.DataFieldModle;
import com.xwke.spider.vo.DataFieldVo;
import com.xwke.spider.vo.DataTableVo;
import com.xwke.spider.web.service.UserDataService;

@Controller
@RequestMapping(value = "/u")
public class UserSpiderController {

	@Resource
	UserDataService userDataService;

	@RequestMapping(value = "/main")
	public String main(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {
		String userId = SpringSecurityUtils.getCurrentUserId();
		if (!StringUtil.isEmpty(userId)) {
			long uid = Long.parseLong(userId);
			modelMap.put("page", userDataService.getTableListByUserId(uid, pageNum));
		}

		return "user/main";
	}

	@RequestMapping(value = "/addtable")
	public String addtable(DataTableVo vo, ModelMap modelMap) {
		if (vo.getId() != 0) {
			DataTableVo tarVo = userDataService.getDataTableById(vo.getId());
			String userId = SpringSecurityUtils.getCurrentUserId();
			if (tarVo.getUserId() != Long.parseLong(userId)) {
				return "error/qx";
			}

			tarVo.setKeyWord(vo.getKeyWord());
			modelMap.put("vo", tarVo);
		}
		return "user/add_table";
	}

	@RequestMapping(value = "/saveTable")
	public String saveDatatable(DataTableVo vo) {

		if ("update".equals(vo.getKeyWord())) {

		} else {
			String tableName = RandomUtils.getRandomString(6);
			String userId = SpringSecurityUtils.getCurrentUserId();
			vo.setTableName(tableName);
			vo.setUserId(Long.parseLong(userId));
		}
		userDataService.saveDataTableVo(vo);

		return "redirect:main";
	}

	@RequestMapping(value = "/fieldList")
	public String fieldList(@RequestParam(value = "tid", defaultValue = "0") int tid, ModelMap modelMap) {
		DataTableVo tarVo = userDataService.getDataTableById(tid);
		String userId = SpringSecurityUtils.getCurrentUserId();
		if (tarVo == null || tarVo.getUserId() != Long.parseLong(userId)) {
			return "error/qx";
		}
		List<DataFieldModle> list = userDataService.getFieldListByTid(tid);
		modelMap.put("list", list);
		modelMap.put("table", tarVo);
		return "user/list_field";
	}

	@RequestMapping(value = "/addfield")
	public String addfield(DataFieldVo vo, @RequestParam(value = "tid", defaultValue = "0") int tid,
			ModelMap modelMap) {

		if (vo.getId() != 0) {
			DataFieldVo tarVo = userDataService.getDataFieldById(vo.getId());
			String userId = SpringSecurityUtils.getCurrentUserId();
			DataTableVo tableVo = userDataService.getDataTableById(tid);
			if (tableVo.getUserId() != Long.parseLong(userId)) {
				return "error/qx";
			}
			tarVo.setKeyWord(vo.getKeyWord());
			modelMap.put("vo", tarVo);
		}
		return "user/add_field";
	}

	@RequestMapping(value = "/saveField")
	public String saveFieldtable(DataFieldVo vo) {

		if ("update".equals(vo.getKeyWord())) {

		} else {
			String tableName = RandomUtils.getRandomString(4);
			vo.setField_name(tableName);
			vo.setField_type("string");
		}
		userDataService.saveDataFieldVo(vo);

		return "redirect:fieldList";
	}

	@RequestMapping(value = "/ruleList")
	public String ruleList(MultipartFile upfile) {

		return "user/list_rule";
	}

	@RequestMapping(value = "/timeList")
	public String timeList(MultipartFile upfile) {

		return "user/list_times";
	}

}
