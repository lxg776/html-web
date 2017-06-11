package test.huntsman;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xwke.base.core.dao.UserDao;
import com.xwke.modle.UserModle;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.dao.NewsDao;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration("/spring/spring-mybatis.xml")
public class TestInsert {

	
	
	@Resource(name="jxGovConfig")
	NewsConfiguration jxGovConfig;
	
	
	public void test01() {
		
//		UserModle user = new UserModle();
//		NewsModle n =new NewsModle();
//		n.setAuthor("lxg777");
//		n.setTitle("wtf");
//		 newDao.add(n);
	}
	
	@Test
	public void testJxConfig(){
		String text="<span>2017-06-09 16:38:41&nbsp;&nbsp;&nbsp;来源：百色新闻网&nbsp;&nbsp;&nbsp;评论：<a href=\"http://www.jingxi.gov.cn/index.php?m=content&amp;c=index&amp;a=show&amp;catid=22&amp;id=42061#comment_iframe\" id=\"comment\">0</a> 点击：</span>";
		text=text.replaceAll("&nbsp;", "");
		String time=text.substring(text.indexOf("<span>"), text.indexOf("来源："));
		String source=text.substring(text.indexOf("来源："), text.indexOf("评论："));
		System.out.println(time);
		System.out.println(source);
		
		
		String imgUrl="http://avatar.csdn.net/B/9/0/1_mp19901204.jpg.png";
		
		System.out.println(imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length()));
		//System.out.println(jxGovConfig.getSite("123").getDomain());
	}
}
