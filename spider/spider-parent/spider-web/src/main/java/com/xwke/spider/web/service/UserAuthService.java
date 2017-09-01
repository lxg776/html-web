package com.xwke.spider.web.service;

import com.xwke.spider.modle.UserAuthModle;

public interface UserAuthService {

	UserAuthModle findByUsername(String username);

	UserAuthModle findByRef(String ref, String tenantId);

	String findPassword(String username, String tenantId);
}
