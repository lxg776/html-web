import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.spider.elasticsearch.modle.EsNewsModle;
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

		EsNewsModle modle = new EsNewsModle();
		modle.setTitle("wtf999");
		modle.setAuthor("haha123");
		modle.setContent("abc123");
		modle.setPubTime("2011-05-06");

		esService.saveNews(modle);
		
		
		// EsNewsModle modle = esService.getNewsById("AV4CcjFO983ttRWfi_cH");
		// modle.setContent("wtf0001");
		// esService.updateEsNews(modle);

	}
	
	
	@Test
	public void testSearch() {
		/// Settings settings = Settings.builder().put("cluster.name",
		/// "xxxxx-elastic").put("client.transport.sniff", true).build();

//		EsNewsModle modle = new EsNewsModle();
//		modle.setTitle("wtf999");
//		modle.setAuthor("haha123");
//		modle.setContent("abc123");
//		modle.setPubTime("2011-05-06");
//
//		esService.saveNews(modle);
		
		
		// EsNewsModle modle = esService.getNewsById("AV4CcjFO983ttRWfi_cH");
		// modle.setContent("wtf0001");
		// esService.updateEsNews(modle);
		
		

	}


}
