package com.xwke.spider.huntsman.configuration;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwke.spider.huntsman.util.FileHelper;
import us.codecraft.webmagic.Site;

public class NewsConfiguration {

	private static Logger logger = Logger.getLogger(NewsConfiguration.class);

	private String path;
	private String config;
	private Site site;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public NewsConfiguration() {

	}

	public NewsConfiguration(String path) {
		// this.path = path;
		this.path = this.getClass().getResource("/").getPath() + path;

		// System.out.println(path);
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
	/**
	 * 缩略图片站点
	 */
	private String thumbnailSite;

	public String getThumbnailSite() {
		return thumbnailSite;
	}

	public void setThumbnailSite(String thumbnailSite) {
		this.thumbnailSite = thumbnailSite;
	}

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
		logger.debug("jxConfigPath:" + path);
		config = FileHelper.getRawText(path);
		logger.debug("jxConfig:" + config);

		JSONObject jsonObject = JSON.parseObject(config);
		site = JSON.parseObject(jsonObject.getString("site"), Site.class);
		setImgFolder(jsonObject.getString("imgFolder"));
		setThumbnail(jsonObject.getString("thumbnail"));
		setColumnId(jsonObject.getString("columnId"));
		setPictureSite(jsonObject.getString("pictureSite"));
		setThumbnailSite(jsonObject.getString("thumbnailSite"));

	}

}
