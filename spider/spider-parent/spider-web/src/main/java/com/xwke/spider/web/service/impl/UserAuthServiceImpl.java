package com.xwke.spider.web.service.impl;

import javax.annotation.Resource;



import com.xwke.spider.dao.UserAuthDao;
import com.xwke.spider.modle.UserAuthModle;
import com.xwke.spider.web.service.UserAuthService;


public class UserAuthServiceImpl implements UserAuthService {

	@Resource
	UserAuthDao userAuthDao;

	@Override
	public UserAuthModle findByUsername(String username) {

		return userAuthDao.findByUsername(username);
	}

	@Override
	public UserAuthModle findByRef(String ref, String tenantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPassword(String username, String tenantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
