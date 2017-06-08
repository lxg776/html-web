package com.xwke.base.core.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.xwke.modle.UserModle;

@Repository
public class UserDao extends DaoImpl<UserModle, Serializable> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

}
