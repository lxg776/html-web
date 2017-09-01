package com.xwke.spider.dao;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.UserAuthModle;

@Repository
public class UserAuthDao extends DaoImpl<UserAuthModle, Serializable> {

	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public UserAuthModle findByUsername(String username) {
		String sql = String.format(
				"select id,username,password,status,user_repo_ref as ref from s_user_status where username = '%s'",
				username);
		Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne("getById", sql);
		
		
		UserAuthModle modle = CommonUtil.injectBean(UserAuthModle.class, resultMap);
		return modle;
	}

}
