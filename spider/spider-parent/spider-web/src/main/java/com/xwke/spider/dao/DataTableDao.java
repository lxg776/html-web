package com.xwke.spider.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.util.StringUtil;
import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.DataTableModle;
import com.xwke.spider.modle.NewsModle;

@Repository
public class DataTableDao extends DaoImpl<DataTableModle, Serializable> {

	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public List<DataTableModle> getTableListByUserId(long userId) {
		String sql = String.format(
				"select id,table_name as tableName ,show_table_name as tableShowName,user_id as userId from sd_data_table where user_id=%d",
				userId);

		return getlistBySql(sql);

	}

	public DataTableModle getDataById(long id) {
		// TODO Auto-generated method stub
		String sql = String.format(
				"select id,table_name as tableName ,show_table_name as tableShowName,user_id as userId from sd_data_table where id=%d",
				id);
		Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne("getById", sql);

		return CommonUtil.injectBean(DataTableModle.class, resultMap);

	}

}
