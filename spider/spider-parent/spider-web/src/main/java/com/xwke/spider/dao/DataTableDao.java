package com.xwke.spider.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.util.StringUtil;
import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.DataTableModle;
import com.xwke.spider.modle.NewsModle;

@Repository
public class DataTableDao extends DaoImpl<DataTableModle, Serializable> {

	public List<DataTableModle> getTableListByUserId(long userId) {
		String sql = String.format("select id,table_name as tableName ,show_table_name as tableShowName,user_id as userId from sd_data_table where user_id=%d",
				userId);

		return getlistBySql(sql);

	}

}
