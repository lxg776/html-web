
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.spider.web.service.ImageRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml" })
public class Test02 {
	@Resource
	ImageRecordService imageRecordService;

	@Test
	public void test01() {
//		ImageRecordModle modle = new ImageRecordModle();
//		modle.setNewsId(10);
//		modle.setImageUrl("http://www.baidu.com");
//		modle.setLoadCount(3);
//		modle.setSavePath("d://ww1/12/88");
//		modle.setStatus("success");
//		imageRecordService.addImageRecord(modle);
		// nodeService.addTag(new Long(7), new Long(7));

	}

}
