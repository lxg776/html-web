package com.xwke.spider.dao;

import java.io.Serializable;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.DataOperationModle;

@Repository
public class DataOperationDao extends DaoImpl<DataOperationModle, Serializable> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public int addRelByOperationAndExector(int exectorId, int operationId) {
		// TODO Auto-generated method stub

		String sql = String.format("insert into s_executor_operation_rel(executor_id,operation_id)values(%d,%d)",
				exectorId, operationId);
		logger.debug(sql);
		return sqlSessionTemplateASS.insert("addLocal", sql);

	}
	
	

	public int delRelByOperationAndExector(int exectorId, int operationId) {
		// TODO Auto-generated method stub

		String sql = String.format("delete from s_executor_operation_rel where executor_id=%d and operation_id=%d",
				exectorId, operationId);
		logger.debug(sql);
		return sqlSessionTemplateASS.delete("deleteByparm", sql);

	}
	
	


}
