package com.xwke.spider.huntsman;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.base.core.dao.UserDao;
import com.xwke.modle.UserModle;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration("/spring/spring-mybatis.xml")

public class TestInsert {

	@Resource
	UserDao userDao;

	@Test
	public void test01() {

		UserModle user = new UserModle();
		user.setUserName("lx502");
		user.setPwd("wtf878");
		userDao.add(user);

	}

}
