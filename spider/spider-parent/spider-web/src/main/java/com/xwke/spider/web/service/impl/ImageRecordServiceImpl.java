package com.xwke.spider.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.ImageRecordDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.ImageRecordModle;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.modle.SiteConfigModle;
import com.xwke.spider.vo.NewsModleVo;
import com.xwke.spider.web.service.ImageRecordService;

@Service
public class ImageRecordServiceImpl implements ImageRecordService {

	@Resource
	ImageRecordDao imageRecordDao;

	/**
	 * 添加图片数据
	 * 
	 * @param modle
	 */
	@Override
	public void addImageRecord(ImageRecordModle modle) {
		imageRecordDao.addLocal(modle);
	}
	
	@Override
	public PageOnterModle getList(int pageNum) {
		Page<NewsColumnModle> pageonter = PageHelper.startPage(pageNum, 20);
		imageRecordDao.list(new WherePrams(null, null, null));
		PageOnterModle page = CommonUtil.getPageOnter(pageonter);

		return page;
	}

}
