package com.xwke.spider.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.DataFieldModle;

@Repository
public class DataFieldDao extends DaoImpl<DataFieldModle, Serializable> {

	public List<DataFieldModle> getFieldsByTid(long tid) {

		String sql = String
				.format("select id,field_name,show_field_name,field_type,t_id from sd_field_table where t_id=%d", tid);
		return getlistBySql(sql);
	}
	
	


}
