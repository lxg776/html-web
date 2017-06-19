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
	
	
	
	public void getList(){
		
	}

}
