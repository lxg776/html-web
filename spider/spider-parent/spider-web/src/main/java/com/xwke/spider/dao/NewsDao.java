package com.xwke.spider.dao;

import java.io.Serializable;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.util.StringUtil;
import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.NewsModle;

@Repository
public class NewsDao extends DaoImpl<NewsModle, Serializable> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public long getCountBySourceUrl(String url) {
		// TODO Auto-generated method stub

		String sql = "select count(id) from " + getTableName() + " where source_url = '" + url + "'";
		logger.debug(sql);
		return sqlSessionTemplateASS.selectOne("getCountBySourceUrl", sql);

	}

	public boolean isExistBySource(String sourceUrl) {
		if (StringUtil.isEmpty(sourceUrl)) {
			return true;
		}
		long count = getCountBySourceUrl(sourceUrl);
		if (count <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 添加新闻
	 * 
	 * @param newsModle
	 */
	public int addNews(NewsModle newsModle) {
		if (!isExistBySource(newsModle.getSourceUrl())) {
			return add(newsModle);
		}

		return -1;
	}
}
