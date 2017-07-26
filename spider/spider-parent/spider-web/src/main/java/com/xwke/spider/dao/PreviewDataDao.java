//package com.xwke.spider.dao;
//
//import java.io.Serializable;
//import java.util.Map;
//import javax.annotation.Resource;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.stereotype.Repository;
//import com.xwke.base.core.dao.DaoImpl;
//import com.xwke.spider.modle.PreviewDataModle;
//
//@Repository
//public class PreviewDataDao extends DaoImpl<PreviewDataModle, Serializable> {
//
//	@Resource
//	private SqlSessionTemplate sqlSessionTemplateASS;
//
//	/**
//	 * 添加或者更新预览数据
//	 * 
//	 * @param modle
//	 */
//	public int addOrUpdatePreviewData(PreviewDataModle modle) {
//
//		long executorId = modle.getExecutorId();
//		String type = modle.getType();
//		if (getCountByExecutorIdAndTpy(executorId, type) > 0) {
//			String sql = String.format(
//					"update " + getTableName() + " set html_data = %s where d_type = '%s' and executor_id = %d ",
//					modle.getHtmlData(), type, executorId);
//			return sqlSessionTemplateASS.update("updateByPram", sql);
//		} else {
//			return addLocal(modle);
//		}
//
//	}
//
//	public long getCountByExecutorIdAndTpy(long executorId, String type) {
//		// TODO Auto-generated method stub
//
//		String sql = String.format(
//				"select count(id) from " + getTableName() + " where d_type = '%s' and executor_id = %d", type,
//				executorId);
//		logger.debug(sql);
//		return sqlSessionTemplateASS.selectOne("getCountBySourceUrl", sql);
//
//	}
//
//	public PreviewDataModle getModleByExecutorIdAndType(long executorId, String type) {
//
//		String sql = String.format("select * from" + getTableName() + " where  d_type = '%s' and executor_id = %d",
//				type, executorId);
//		Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne("getById", sql);
//		return handleResult(resultMap, PreviewDataModle.class);
//	}
//
//}
