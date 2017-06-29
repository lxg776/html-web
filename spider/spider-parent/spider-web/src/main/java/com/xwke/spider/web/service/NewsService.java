package com.xwke.spider.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.NewsModleVo;

@Service
public class NewsService {

	@Resource
	NewsDao newsDao;

	public PageOnterModle getNewsList(int pageNum) {
		Page<NewsColumnModle> pageonter = PageHelper.startPage(pageNum, 20);
		List<NewsModle> newsList = newsDao.list(new WherePrams("column_id", "=", 0));
		PageOnterModle page = CommonUtil.getPageOnter(pageonter);

		List<NewsModleVo> voList = new ArrayList<>();
		for (NewsModle item : newsList) {
			NewsModleVo vo = item.getTargetObject(NewsModleVo.class);
			vo.setImgList(JSONArray.parseArray(vo.getImagesJsonStr(), String.class));
			vo.setThumbnailList(JSONArray.parseArray(vo.getThumImg(), String.class));
			voList.add(vo);
		}
		page.setDataList(voList);
		return page;
	}

}
