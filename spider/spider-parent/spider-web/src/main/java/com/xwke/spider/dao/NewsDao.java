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
	public int addNewsAndRel(NewsModle newsModle, String[] nodeIds, String status) {
		if (!isExistBySource(newsModle.getSourceUrl())) {

			add(newsModle);
			if (nodeIds != null && nodeIds.length > 0) {
				for (int i = 0; i < nodeIds.length; i++) {
					addNewsRel(new Long(nodeIds[i]), newsModle.getId(), status);
				}
			}
		}

		return -1;
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

	/**
	 * 添加关系
	 * 
	 * @param nodeId
	 * @param newsId
	 * @param status
	 * @return
	 */
	public int addNewsRel(Long nodeId, Long newsId, String status) {

		String tableName = "s_node_news_relation";
		String sql = String.format("select count(id) from " + tableName + " where node_id =  %d and news_id = %d",
				nodeId, newsId);
		int count = sqlSessionTemplateASS.selectOne("getCountBySourceUrl", sql);
		String insertSql = String.format("insert into  " + tableName + "(node_id,newsId,pub_status)values(%d,%d,'%s')",
				nodeId, newsId, status);
		if (count <= 0) {
			return sqlSessionTemplateASS.insert("add", insertSql);
		}
		return -1;
	}
}
