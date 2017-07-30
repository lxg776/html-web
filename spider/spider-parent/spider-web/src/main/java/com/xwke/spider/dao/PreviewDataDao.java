package com.xwke.spider.dao;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.xwke.base.core.dao.DaoImpl;
import com.xwke.base.core.sql.where.SqlUtil;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.PreviewDataModle;

@Repository
public class PreviewDataDao extends DaoImpl<PreviewDataModle, Serializable> {

	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	/**
	 * 添加或者更新预览数据
	 * 
	 * @param modle
	 */
	public int addOrUpdatePreviewData(PreviewDataModle modle) {

		long executorId = modle.getExecutorId();
		String type = modle.getType();
		if (getCountByExecutorIdAndTpy(executorId, type) > 0) {
			return sqlSessionTemplateASS.update("updateHtml", modle);
		} else {
			return sqlSessionTemplateASS.update("insertHtml", modle);

		}

	}

	public long getCountByExecutorIdAndTpy(long executorId, String type) {
		// TODO Auto-generated method stub

		String sql = String.format(
				"select count(id) from " + getTableName() + " where d_type = '%s' and executor_id = %d", type,
				executorId);
		logger.debug(sql);
		return sqlSessionTemplateASS.selectOne("getCountBySourceUrl", sql);

	}

	public PreviewDataModle getModleByExecutorIdAndType(long executorId, String type) {

		String sql = String.format("select id,d_type as type,html_data as htmlData,executor_id as executorId from " + getTableName() + " where  d_type = '%s' and executor_id = %d",
				type, executorId);
		Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne("getById", sql);
		
		PreviewDataModle modle = CommonUtil.injectBean(PreviewDataModle.class, resultMap);
		
		return modle;
	}

}
