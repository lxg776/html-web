
import org.junit.Test;

import com.xwke.spider.huntsman.util.HtmlUtil;

public class TestString {
	@Test
	public void test02() {
		String text = "<h1>我市上半年接待游客逾300万人 </h1>";
		// sql = String.format(sql, new Long(2), new Long(1));
		text = HtmlUtil.delHTMLTag(text);
		System.out.println(text);
//		System.out.println(123123);
	}

}
