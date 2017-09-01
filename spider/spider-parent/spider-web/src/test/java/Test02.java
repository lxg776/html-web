import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.api.tenant.TenantHelper;
import com.xwke.base.core.dao.UserDao;
import com.xwke.spider.dao.UserAuthDao;
import com.xwke.spider.huntsman.NewsSpiderHunter;
import com.xwke.spider.modle.UserAuthModle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.service.ScheduleJobService;
import com.xwke.spider.web.service.UserAuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml","classpath:spring/spring-mybatis.xml" })
public class Test02 {

	// @Resource
	// PreviewHunter hunter;
	// @Resource
	// ExecutorService service;

//	@Resource
//	NewsSpiderHunter hunter;
//	@Resource
//	ScheduleJobService scheduleJobService;
	
	@Resource
	UserAuthDao userAuthService;

	@Test
	public void testHtml() {
		
		UserAuthModle userAuth = userAuthService.findByUsername("lxg776");
		System.out.print(userAuth.getId());

		
	}

}
