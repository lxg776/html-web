
import org.junit.Test;

import com.xwke.spider.huntsman.util.HtmlUtil;

public class TestString {
	@Test
	public void test02() {
		String text = "http://ent.news.cn/2017-08/08/c_1121446895.htm";
		// sql = String.format(sql, new Long(2), new Long(1));
		//text = HtmlUtil.delHTMLTag(text);
		System.out.println(text.substring(0, text.lastIndexOf("/")));
//		System.out.println(123123);
	}

}
