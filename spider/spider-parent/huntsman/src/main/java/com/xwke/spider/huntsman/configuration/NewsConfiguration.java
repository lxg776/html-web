package com.xwke.spider.huntsman.configuration;



import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwke.spider.huntsman.util.FileHelper;
import us.codecraft.webmagic.Site;


public class NewsConfiguration {

	String path;
	String config;
	Site site;

	public NewsConfiguration(String path) {
		// this.path = path;
		this.path = this.getClass().getResource("/").getPath()+path;
		resolve();
	}
	
	
	
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}




	/**
	 * 图片存放的文件夹
	 */
	private String imgFolder;
	/**
	 * 缩略图存放的文件夹
	 */
	private String thumbnail;
	/**
	 * 抓取进去的栏目id
	 */
	private String columnId;
	
	/**
	 * 图片的站点
	 */
	private String pictureSite;
	
	
	

	public String getPictureSite() {
		return pictureSite;
	}




	public void setPictureSite(String pictureSite) {
		this.pictureSite = pictureSite;
	}




	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getImgFolder() {
		return imgFolder;
	}

	public void setImgFolder(String imgFolder) {
		this.imgFolder = imgFolder;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * 解析的方法
	 */
	
	protected void resolve() {
		// TODO Auto-generated method stub
		config = FileHelper.getRawText(path);

		JSONObject jsonObject = JSON.parseObject(config);
		site = JSON.parseObject(jsonObject.getString("site"), Site.class);
		setImgFolder(jsonObject.getString("imgFolder"));
		setThumbnail(jsonObject.getString("thumbnail"));
		setColumnId(jsonObject.getString("columnId"));
		setPictureSite(jsonObject.getString("pictureSite"));
	}

}
