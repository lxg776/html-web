import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.api.tenant.TenantHelper;
import com.xwke.spider.huntsman.NewsSpiderHunter;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.service.ScheduleJobService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class Test02 {

	// @Resource
	// PreviewHunter hunter;
	// @Resource
	// ExecutorService service;

	@Resource
	NewsSpiderHunter hunter;
	@Resource
	ScheduleJobService scheduleJobService;

	@Test
	public void testHtml() {
		
//		ScheduleJob job = scheduleJobService.get(new Long(3)).getTargetObject(ScheduleJob.class);
//		hunter.crawl(job);
		
//		TenantHelper.getTenantDto().setRef("001");
//		
//		System.out.println("123123"+TenantHelper.getTenantDto().getRef());
		
	}

}
