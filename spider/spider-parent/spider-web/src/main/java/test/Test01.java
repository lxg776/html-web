package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.modle.NewsColumnModle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-beans.xml", "classpath:spring/spring-mybatis.xml" })
public class Test01 {
	@Resource
	NewsCoumnDao newsCoumnDao;

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

}