import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.spider.elasticsearch.modle.BulidMode;
import com.xwke.spider.elasticsearch.modle.FileKeyModle;
import com.xwke.spider.elasticsearch.service.EsNewsServiceImpy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class TestEs {

	// @Resource
	// PreviewHunter hunter;
	// @Resource
	// ExecutorService service;

	@Resource
	EsNewsServiceImpy esService;

	@Test
	public void testHtml() {
		/// Settings settings = Settings.builder().put("cluster.name",
		/// "xxxxx-elastic").put("client.transport.sniff", true).build();

		// EsNewsModle modle = new EsNewsModle();
		// modle.setTitle("wtf999");
		// modle.setAuthor("haha123");
		// modle.setContent("abc123");
		// modle.setPubTime("2011-05-06");

		BulidMode modle = new BulidMode();
		modle.setObjectName("properties");

		List<BulidMode> chilidList = new ArrayList<>();

		BulidMode item1 = new BulidMode();
		item1.setObjectName("title1");
		FileKeyModle keyModle = new FileKeyModle();
		keyModle.setKey("type");
		keyModle.setValue("string");

		List<FileKeyModle> fileList = new ArrayList();
		fileList.add(keyModle);
		item1.setFileList(fileList);
		chilidList.add(item1);
		
		
		
		
		BulidMode item2 = new BulidMode();
		item2.setObjectName("title2");
		FileKeyModle keyModle2 = new FileKeyModle();
		keyModle2.setKey("type");
		keyModle2.setValue("string");
		List<FileKeyModle> fileList2 = new ArrayList();
		fileList2.add(keyModle2);
		item2.setFileList(fileList2);
		chilidList.add(item2);
		
		modle.setChlidObject(chilidList);

		try {
			esService.createMapping("news", "test999", modle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// EsNewsModle modle = esService.getNewsById("AV4CcjFO983ttRWfi_cH");
		// modle.setContent("wtf0001");
		// esService.saveNews(modle);

	}

	@Test
	public void testSearch() {
		/// Settings settings = Settings.builder().put("cluster.name",
		/// "xxxxx-elastic").put("client.transport.sniff", true).build();

		// EsNewsModle modle = new EsNewsModle();
		// modle.setTitle("wtf999");
		// modle.setAuthor("haha123");
		// modle.setContent("abc123");
		// modle.setPubTime("2011-05-06");
		//
		// esService.saveNews(modle);

		// EsNewsModle modle = esService.getNewsById("AV4CcjFO983ttRWfi_cH");
		// modle.setContent("wtf0001");
		// esService.updateEsNews(modle);

	}

}
