package com.xwke.spider.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xwke.spider.huntsman.util.LoadPropertiesDataUtils;
import com.xwke.spider.huntsman.util.RandomUtils;
import com.xwke.spider.huntsman.util.StringUtils;

/**
 * 上传图片的controller
 * 
 * @author liuyazhuang
 * 
 */
@Controller
@RequestMapping(value = "/resource/upload")
public class UploadImageController {

	protected static Logger logger = LoggerFactory.getLogger(UploadImageController.class);

	/**
	 * 上传图片
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	//, produces = "text/html"
	@ResponseBody
	@RequestMapping(value = "/images")
	public Map<String, Object> images2(MultipartFile upfile) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String basePath = LoadPropertiesDataUtils.getValue("lyz.uploading.url");
			String visitUrl = LoadPropertiesDataUtils.getValue("lyz.visit.url");
			if (basePath == null || "".equals(basePath)) {
				basePath = "d:/lyz/static"; // 与properties文件中lyz.uploading.url相同，未读取到文件数据时为basePath赋默认值
			}
			if (visitUrl == null || "".equals(visitUrl)) {
				visitUrl = "/upload/"; // 与properties文件中lyz.visit.url相同，未读取到文件数据时为visitUrl赋默认值
			}
			String ext = StringUtils.getExt(upfile.getOriginalFilename());
			String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(RandomUtils.getRandom(6))
					.concat(".").concat(ext);
			StringBuilder sb = new StringBuilder();
			// 拼接保存路径
			sb.append(basePath).append("/").append(fileName);
			visitUrl = visitUrl.concat(fileName);
			File f = new File(sb.toString());
			if (!f.exists()) {
				f.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(f);
			FileCopyUtils.copy(upfile.getInputStream(), out);
			params.put("state", "SUCCESS");
			params.put("url", visitUrl);
			params.put("size", upfile.getSize());
			params.put("original", fileName);
			params.put("type", upfile.getContentType());
		} catch (Exception e) {
			params.put("state", "ERROR");
		}
		return params;
	}

//	@ResponseBody
//	@RequestMapping(value = "/images2")
//	public Map<String, Object> images(HttpServletRequest request) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		Uploader up = new Uploader(request);
//
//		String basePath = LoadPropertiesDataUtils.getValue("lyz.uploading.url");
//		String visitUrl = LoadPropertiesDataUtils.getValue("lyz.visit.url");
//		logger.debug(basePath);
//		up.setSavePath(basePath);
//		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
//		up.setAllowFiles(fileType);
//		up.setMaxSize(10000); // 单位KB
//		try {
//			up.upload();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//			logger.error(e.getMessage());
//		}
//		String callback = request.getParameter("callback");
//		if (callback == null) {
//			params.put("result", "<script>" + callback + "</script>");
//			return params;
//		} else {
//			params.put("state", up.getState());
//			params.put("url", up.getUrl());
//			params.put("size", up.getSize());
//			params.put("originalName", up.getOriginalName());
//			params.put("type", up.getType());
//			return params;
//		}
//
//	}
}