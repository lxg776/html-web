import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.spider.huntsman.PreviewHunter;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class Test02 {

	@Resource
	PreviewHunter hunter;
	@Resource
	ExecutorService service;

	@Test
	public void testHtml() {

		ExectorVo vo = service.getExecutorById(1);

		hunter.crawl(vo);

		// ResultItems result =
		// Spider.create(pageProcessor).get("http://webmagic.io/docs/")

		// String text=page.getHtml().regex("(?<=\\bre)\\w+\\b").get();

		// System.out.println(text);
	}

}
