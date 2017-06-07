package com.xwke.base.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.xwke.base.core.dao.UserDao;
import com.xwke.modle.UserModle;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration("/spring/spring1-test.xml")
public class TestDao {

	// UserDao userDao =new UserDao();

	@Resource
	UserDao userDao;

	@Test
	public void testInsertUser() {
		// UserModle user =new UserModle();
		// userDao.add(user);

		UserModle user = new UserModle();
		user.setUserName("lxg776");
		user.setPwd("fuck123");
		userDao.add(user);
		System.out.println(userDao);

	}

}
