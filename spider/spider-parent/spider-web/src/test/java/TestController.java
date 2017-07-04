import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xwke.spider.web.controller.ContentTagController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-job.xml",
		"classpath:spring/spring-mybatis.xml", "classpath:spring/spring-mvc2.xml" })

public class TestController {
	private MockMvc mockMvc;
	@Autowired
	private ContentTagController indexController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	/**
	 * 主页
	 */
	@Test
	public void testIndex() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/index.html"))
//				.andExpect(MockMvcResultMatchers.view().name("/index")).andReturn();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/tag/saveTag").param("tagName", "tag01").param("remarks", "你好"));
		
		
	 //mockMvc.perform((post("/loginTest").param("userName", "admin").param("password", "123"))).andExpect(status().isOk()) 
		
	}

}