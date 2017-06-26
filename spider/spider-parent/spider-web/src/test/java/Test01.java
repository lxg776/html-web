
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.dao.SiteConfigDao;
import com.xwke.spider.huntsman.JxGovPageHunter;
import com.xwke.spider.huntsman.job.ScheduleService;
import com.xwke.spider.huntsman.util.FileHelper;
import com.xwke.spider.modle.SiteConfigModle;
import com.xwke.spider.quartz.service.ScheduleJobService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class Test01 {
	@Resource
	NewsCoumnDao newsCoumnDao;
	@Resource
	ScheduleService service;

	@Autowired
	private ScheduleJobService scheduleJobService;

	@Test
	public void test01() {
		// for (int i = 0; i < 100; i++) {
		// NewsColumnModle item = new NewsColumnModle();
		// item.setColumnDescribe("lxg776");
		// item.setColumnName("wtf" + i);
		// newsCoumnDao.add(item);
		// }

		// Page<NewsColumnModle> pageonter = PageHelper.startPage(6, 11);
		//
		// List<NewsColumnModle> list = newsCoumnDao.list(new WherePrams(null,
		// null, null));
		// for (NewsColumnModle item : list) {
		// System.out.println(item.getId());
		// System.out.println(item.getColumnName());
		// }
		// System.out.println(pageonter.getPageNum());
		// System.out.println(pageonter.getPages());
		// System.out.println(pageonter.getTotal());
		// System.out.println(pageonter.);
	}

	//
	// @Test
	// public void testJob(){
	// try {
	// Thread.sleep(10*1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// ScheduledJobSetting setting = new ScheduledJobSetting();
	// setting.setClassPath("com.xwke.spider.huntsman.job");
	// setting.setCron("0/10 * * * * ? ");
	// setting.setGroupId("wtfid");
	// setting.setScheduleId("wtfid");
	// setting.setId(99);
	//
	// try {
	// service.updateJobSetting(setting);
	// } catch (SchedulerException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// Thread.sleep(100*1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	@Resource
	JxGovPageHunter hunter;

	@Test
	public void testJob2() {

		// ScheduleJobVo scheduleJobVo =new ScheduleJobVo();
		// scheduleJobVo.setAliasName("wtf110");
		// scheduleJobVo.setCronExpression("0/10 * * * * ? ");
		// scheduleJobVo.setDescription("哈哈");
		// //scheduleJobVo.setIsSync(false);
		// scheduleJobVo.setExecutor("wtfhaha");
		// scheduleJobVo.setJobGroup("wtf1");
		// scheduleJobVo.setJobName("my01");
		// scheduleJobVo.setExecutor("jxgov_spider");
		// scheduleJobService.insert(scheduleJobVo);
		String[] urls = "http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=22,http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=26"
				.split(",");
		hunter.crawl(urls);

	}

	@Resource
	SiteConfigDao siteDao;

	@Test
	public void testJob3() {

		// ScheduleJobVo scheduleJobVo =new ScheduleJobVo();
		// scheduleJobVo.setAliasName("wtf110");
		// scheduleJobVo.setCronExpression("0/10 * * * * ? ");
		// scheduleJobVo.setDescription("哈哈");
		// //scheduleJobVo.setIsSync(false);
		// scheduleJobVo.setExecutor("wtfhaha");
		// scheduleJobVo.setJobGroup("wtf1");
		// scheduleJobVo.setJobName("my01");
		// scheduleJobVo.setExecutor("jxgov_spider");
		// scheduleJobService.insert(scheduleJobVo);
		// String[] urls =
		// "http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=22,http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=26"
		// .split(",");
		// hunter.crawl();
		//
		// SiteConfigModle modle = new SiteConfigModle();
		// modle.setAlias("jxGov");
		// String text = FileHelper
		// .getRawText(this.getClass().getResource("/").getPath() +
		// "/site-config/jxgov-config.json");
		// modle.setConfigJsonText(text);
		// siteDao.add(modle);

		// SiteConfigModle siteConfigModle = new SiteConfigModle();
		SiteConfigModle siteConfigModle = siteDao.list(new WherePrams("c_alias", " = ", "jxGov")).get(0);
		System.out.println(siteConfigModle.getConfigJsonText());
	}

}
