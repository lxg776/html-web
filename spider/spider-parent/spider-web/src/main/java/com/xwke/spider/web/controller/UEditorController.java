package com.xwke.spider.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;

@Controller
public class UEditorController {

	private String rootPath = null;

	@RequestMapping("/ued/config")
	public void index(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			String tmpRootPath = request.getServletContext().getRealPath("/");
			// String contextPath = request.getContextPath();
			// if(null==contextPath ||"".equals(contextPath)){
			// contextPath="";
			// }
			response.getWriter()
					.write(new ActionEnter(request, (rootPath != null && !"".equals(rootPath)) ? rootPath : tmpRootPath)
							.exec());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getRootPath() {
		return this.rootPath;
	}

}