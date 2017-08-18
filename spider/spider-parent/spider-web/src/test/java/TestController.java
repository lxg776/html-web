import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xwke.spider.web.controller.UploadImageController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-mvc2.xml" })

public class TestController {
	private MockMvc mockMvc;

	@Autowired
	private UploadImageController indexController;

	private HttpServletRequest request;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		// 创建request和response的Mock
		request = (HttpServletRequest) EasyMock.createMock(HttpServletRequest.class);
		// response = (HttpServletResponse)
		// EasyMock.createMock(HttpServletResponse.class);

	}

	/**
	 * 主页
	 */
	@Test
	public void testIndex() throws Exception {
		// mockMvc.perform(MockMvcRequestBuilders.get("/index.html"))
		// .andExpect(MockMvcResultMatchers.view().name("/index")).andReturn();

		// mockMvc.perform(MockMvcRequestBuilders.post("/node/add").param("node_name",
		// "tag01").param("sort", "2"));

		// mockMvc.perform(MockMvcRequestBuilders.get("/news/newsList").param("nodeId",
		// "23").param("keyWord", "市"));
		// mockMvc.perform((MockMvcRequestBuilders.post("/executor/preview").param("executorId",
		// "1").param("keyWord", "onLine")));
		// mockMvc.perform((MockMvcRequestBuilders.get("/job/input-schedule-job").param("scheduleJobId",
		// "2").param("keywords", "delUpdate")));

		// mockMvc.perform((MockMvcRequestBuilders.post("/executor/preview").param("executorId",
		// "3").param("keyWord", "local")));
		
		
		MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");
        FileInputStream fis = new FileInputStream("/Users/sunshine/Desktop/01.jpg");
        
        MockMultipartFile mfile = new MockMultipartFile( "/Users/sunshine/Desktop/01.jpg","/Users/sunshine/Desktop/01.jpg",null, fis);
        this.indexController.images2(mfile);

	}

}
