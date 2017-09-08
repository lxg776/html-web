package com.xwke.spider.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/u")
public class UserSpiderController {

	@RequestMapping(value = "/main")
	public String main(MultipartFile upfile) {

		return "user/main";
	}
	
	
	@RequestMapping(value = "/addtable")
	public String addtable(MultipartFile upfile) {

		return "user/add_table";
	}
	
	
	@RequestMapping(value = "/fieldList")
	public String fieldList(MultipartFile upfile) {

		return "user/list_field";
	}
	
	@RequestMapping(value = "/addfield")
	public String addfield(MultipartFile upfile) {

		return "user/add_field";
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
