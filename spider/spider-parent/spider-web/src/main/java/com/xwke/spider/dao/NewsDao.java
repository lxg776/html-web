package com.xwke.spider.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.NewsModle;

@Repository
public class NewsDao extends DaoImpl<NewsModle, Serializable> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public void getList() {

	}

	public long getCountBySourceUrl(String url) {
		// TODO Auto-generated method stub

		String sql = "select count(id) from " + getTableName() + " where source_url = '" + url+"'";
		logger.debug(sql);
		return sqlSessionTemplateASS.insert("getCountBySourceUrl", sql);

	}

	/**
	 * 添加新闻
	 * 
	 * @param newsModle
	 */
	public void addNews(NewsModle newsModle) {
		long count = getCountBySourceUrl(newsModle.getSourceUrl());
		if (count == 0) {
			addNews(newsModle);
		}

	}
}
