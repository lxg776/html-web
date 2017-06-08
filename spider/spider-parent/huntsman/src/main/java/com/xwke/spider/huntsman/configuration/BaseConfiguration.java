package com.xwke.spider.huntsman.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Site;

public class BaseConfiguration extends AbstractConfiguration {

	protected Site site;
	protected String baseDir;

	public Site getSite() {
		return site;
	}

	public String getBaseDir() {
		return baseDir;
	}
	
	

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	protected void resolve() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = JSON.parseObject(config);
		site = JSON.parseObject(jsonObject.getString("site"), Site.class);
		setBaseDir(jsonObject.getString("base_dir"));
	}

}
