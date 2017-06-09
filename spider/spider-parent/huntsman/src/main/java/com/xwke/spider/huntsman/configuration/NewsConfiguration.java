package com.xwke.spider.huntsman.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Site;

public class NewsConfiguration extends BaseConfiguration {

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
	@Override
	protected void resolve() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = JSON.parseObject(config);
		site = JSON.parseObject(jsonObject.getString("site"), Site.class);
		setImgFolder(jsonObject.getString("imgFolder"));
		setThumbnail(jsonObject.getString("thumbnail"));
		setColumnId(jsonObject.getString("columnId"));
	}

}
