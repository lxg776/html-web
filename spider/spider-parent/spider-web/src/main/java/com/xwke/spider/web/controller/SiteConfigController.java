package com.xwke.spider.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xwke.spider.modle.SiteConfigModle;
import com.xwke.spider.vo.SiteConfigVo;
import com.xwke.spider.web.service.SiteConfigService;

@Controller
public class SiteConfigController {

	public final static String KEY_EDIT = "edit";
	public final static String KEY_DEL = "del";

	@Resource
	SiteConfigService service;

	@RequestMapping(value = "/site/configList", method = RequestMethod.GET)
	public String configList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, ModelMap modelMap) {
		modelMap.addAttribute("page", service.getList(pageNum));
		return "site/site_config_list";
	}

	@RequestMapping(value = "/site/toSave", method = RequestMethod.GET)
	public String toAddConfig(SiteConfigVo tagModle, ModelMap modelMap) {

		if (tagModle.getId() != 0) {

			SiteConfigVo contentTagVo = service.get(tagModle.getId()).getTargetObject(SiteConfigVo.class);
			contentTagVo.setKeyWord(tagModle.getKeyWord());
			modelMap.put("vo", contentTagVo);
		}
		return "site/add_config";

	}

	@RequestMapping(value = "/site/saveConfig", method = RequestMethod.POST)
	public String saveConfig(SiteConfigVo vo) {
		SiteConfigModle configMolde = vo.getTargetObject(SiteConfigModle.class);
		
		if (null == configMolde.getId()) {
			service.addConfig(configMolde);
		} else if (KEY_EDIT.equals(vo.getKeyWord())) {
			service.updateConfig(configMolde);
		} else if (KEY_DEL.equals(vo.getKeyWord())) {
			service.delConfig(configMolde);
		}

		return "redirect:configList";
	}

}
