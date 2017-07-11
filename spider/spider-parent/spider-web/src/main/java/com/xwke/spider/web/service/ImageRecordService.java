package com.xwke.spider.web.service;

import com.xwke.spider.modle.ImageRecordModle;
import com.xwke.spider.modle.PageOnterModle;

public interface ImageRecordService {
	
	/**
	 * 添加图片数据
	 * @param modle
	 */
	public void addImageRecord(ImageRecordModle modle);

	public PageOnterModle getList(int pageNum);

}
