package com.xwke.spider.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.SiteConfigDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.modle.SiteConfigModle;
import com.xwke.spider.web.service.SiteConfigService;

@Service
public class SiteConfigServiceImpl implements SiteConfigService {

	@Resource
	SiteConfigDao siteDao;
	int pageSize = 15;

	@Override
	public PageOnterModle getList(int pageNum) {

		Page<SiteConfigModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<SiteConfigModle> list = siteDao.list(new WherePrams(null, null, null));
		return CommonUtil.getPageOnter(pageonter);
	}

	@Override
	public SiteConfigModle get(Long id) {
		return siteDao.get(id);
	}

	@Override
	public void addConfig(SiteConfigModle siteConfig) {
		// TODO Auto-generated method stub
		siteDao.addLocal(siteConfig);
	}

	@Override
	public int updateConfig(SiteConfigModle configMolde) {
		// TODO Auto-generated method stub
		return siteDao.update(configMolde);
	}

	@Override
	public int delConfig(SiteConfigModle configMolde) {
		// TODO Auto-generated method stub
		return siteDao.del(configMolde.getId());
	}

}
