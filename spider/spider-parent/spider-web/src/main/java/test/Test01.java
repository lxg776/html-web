package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.huntsman.job.ScheduleService;
import com.xwke.spider.huntsman.job.ScheduledJobSetting;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.quartz.service.ScheduleJobService;
import com.xwke.spider.quartz.vo.ScheduleJobVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-mybatis.xml","classpath:spring/spring-job.xml" })
public class Test01 {
	@Resource
	NewsCoumnDao newsCoumnDao;
	@Resource
	ScheduleService service;
	
    @Autowired
    private ScheduleJobService scheduleJobService;

	@Test
	public void test01() {
//		for (int i = 0; i < 100; i++) {
//			NewsColumnModle item = new NewsColumnModle();
//			item.setColumnDescribe("lxg776");
//			item.setColumnName("wtf" + i);
//			newsCoumnDao.add(item);
//		}

		 Page<NewsColumnModle> pageonter=PageHelper.startPage(6, 11);
		
		
		
		 List<NewsColumnModle> list=newsCoumnDao.list(new WherePrams(null,
		 null, null));
		 for(NewsColumnModle item:list){
		 System.out.println(item.getId());
		 System.out.println(item.getColumnName());
		 }
		 System.out.println(pageonter.getPageNum());
		 System.out.println(pageonter.getPages());
		 System.out.println(pageonter.getTotal());
		 //System.out.println(pageonter.);
	}
	
//	
//	@Test
//	public void testJob(){
//		try {
//			Thread.sleep(10*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ScheduledJobSetting setting = new ScheduledJobSetting();
//		setting.setClassPath("com.xwke.spider.huntsman.job");
//		setting.setCron("0/10 * * * * ? ");
//		setting.setGroupId("wtfid");
//		setting.setScheduleId("wtfid");
//		setting.setId(99);
//		
//		try {
//			service.updateJobSetting(setting);
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			Thread.sleep(100*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	@Test
	public void testJob2(){
		
		ScheduleJobVo scheduleJobVo =new ScheduleJobVo();
		scheduleJobVo.setAliasName("wtf110");
		scheduleJobVo.setCronExpression("0/10 * * * * ? ");
		scheduleJobVo.setDescription("哈哈");
		scheduleJobVo.setIsSync(false);
		scheduleJobVo.setJobGroup("wtf");
		scheduleJobVo.setJobName("my name");
		scheduleJobService.insert(scheduleJobVo);
		
		
	}
	
	
	
	
}
