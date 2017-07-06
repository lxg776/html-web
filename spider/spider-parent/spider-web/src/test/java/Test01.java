
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.soap.Node;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.dao.NewsDao;
import com.xwke.spider.dao.NodeDao;
import com.xwke.spider.dao.SiteConfigDao;
import com.xwke.spider.huntsman.JxGovPageHunter;
import com.xwke.spider.huntsman.job.ScheduleService;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.quartz.service.ScheduleJobService;
import com.xwke.spider.vo.NewsModleVo;
import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.ContentTagService;
import com.xwke.spider.web.service.NewsService;
import com.xwke.spider.web.service.NodeService;

import net.coobird.thumbnailator.Thumbnails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class Test01 {
	@Resource
	NewsCoumnDao newsCoumnDao;
	@Resource
	ScheduleService service;

	@Resource
	NewsService newsService;

	@Resource
	ContentTagService tagService;

	@Autowired
	private ScheduleJobService scheduleJobService;

	@Resource
	NodeService nodeService;

	@Resource
	NodeDao nodeDao;

	@Test
	public void test01() {
		// NodeModle nodeModle = new NodeModle();
		// nodeModle.setNodeName("a01");
		// nodeModle.setSort(3);
		// nodeService.add(nodeModle);
		// nodeService.addNode(nodeModle);

		// NodeVo node = new NodeVo();
		// node.setNode_level(0);
		// node.setNodeName("a03");
		// node.setSort(3);
		// nodeService.addRootNode(node);
		// List<NodeModle> nodeList = new ArrayList<>();
		// NodeModle n1 = new NodeModle();
		// n1.setNodeName("d01");
		// n1.setSort(1);
		// nodeList.add(n1);
		// NodeModle n2 = new NodeModle();
		// n2.setNodeName("d02");
		// n2.setSort(1);
		// nodeList.add(n2);
		// nodeService.addChildNode(11, 1, nodeList);
		// List<NodeVo> dataList = nodeDao.getAllNode();
		NodeVo vo = nodeService.getNodeByid(new Long(7));
		System.out.println(vo.getNodeName());

	}

	@Test
	public void test02() {
		String sql = "select count(1) from s_node_relation where f_id = %d and c_id = %d ";
		sql = String.format(sql, new Long(2), new Long(1));

		System.out.println(sql);
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

		Long id = new Long(1);
		ContentTagModle tag = tagService.get(id);
		System.out.println(tag.getTagName());
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
		// hunter.crawl(urls);

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
		//
		// // SiteConfigModle siteConfigModle = new SiteConfigModle();
		// SiteConfigModle siteConfigModle = siteDao.list(new
		// WherePrams("c_alias", " = ", "jxGov")).get(0);
		// System.out.println(siteConfigModle.getConfigJsonText());
		// ScheduleJobVo vo = new ScheduleJobVo();
		// vo.setExecutor("jxwtf");
		// vo.setUrl("wtf00251");
		// vo.setAliasName("kao7");
		// vo.setJobName("bitch7");
		// vo.setDescription("嘎嘎嘎");
		// vo.setCronExpression("0/10 * * * * ?");
		// vo.setJobGroup("fuck");
		//
		//
		// scheduleJobService.insert(vo);
	}

	@Test
	public void testJpeg() {
		//
		// File file = new File("D:/wtf/spider_images/t");
		// if (!file.exists()) {
		// file.mkdirs();
		// }
		//
		// try {
		// Thumbnails.of(new
		// File("D:/wtf/spider_images/images_hd/20170027a189142fb5624251ac626a5839bee375.jpg"))
		// .size(380,
		// 380).outputQuality(1.0f).outputFormat("jpg").toFile("D:/wtf/spider_images/t/101.jpg");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
