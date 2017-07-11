package com.xwke.spider.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xwke.spider.dao.ImageRecordDao;
import com.xwke.spider.modle.ImageRecordModle;
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

}
