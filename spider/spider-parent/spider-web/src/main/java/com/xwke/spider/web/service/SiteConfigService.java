package com.xwke.spider.web.service;

import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.modle.SiteConfigModle;

public interface SiteConfigService {

	public PageOnterModle getList(int pageNum);

	public SiteConfigModle get(Long id);

	public void addConfig(SiteConfigModle siteConfig);

	public int updateConfig(SiteConfigModle configMolde);

	public int delConfig(SiteConfigModle configMolde);
	
	
	

}
