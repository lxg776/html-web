package com.xwke.security.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xwke.security.impl.SpringSecurityUserAuth;
import com.xwke.security.util.BeanMapper;
import com.xwke.spider.modle.UserAuthModle;
import com.xwke.spider.web.service.UserAuthService;

public class DefaultUserDetailsService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(DefaultUserDetailsService.class);
	private UserAuthService userAuthService;
	private BeanMapper beanMapper = new BeanMapper();
	
	

	public UserAuthService getUserAuthService() {
		return userAuthService;
	}

	public void setUserAuthService(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}

	/**
	 * 遇到的问题.
	 * 
	 * 主要流程为 1.判断用户是否存在 2.读取用户权限 3.创建UserDetails
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("username : {}", username);

		if (username == null) {
			logger.info("username is null");
			return null;
		}
		username = username.toLowerCase();
		try {
			UserAuthModle userAuthDto = userAuthService.findByUsername(username);
			SpringSecurityUserAuth userAuthResult = new SpringSecurityUserAuth();
			beanMapper.copy(userAuthDto, userAuthResult);
			return userAuthResult;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new UsernameNotFoundException(username, ex);
		}
	}

	public void setUserAuthConnector(UserAuthService userAuthConnector) {
		this.userAuthService = userAuthConnector;
	}

}
