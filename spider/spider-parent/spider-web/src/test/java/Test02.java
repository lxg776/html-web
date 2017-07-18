


import org.junit.Test;

import us.codecraft.webmagic.selector.Html;



public class Test02 {

	@Test
	public void testHtml() {
		us.codecraft.webmagic.Page page = new us.codecraft.webmagic.Page();
		page.setHtml(new Html(
				"reading a book"));
		
		
		//String text=page.getHtml().regex("(?<=\\bre)\\w+\\b").get();
		
		
		//System.out.println(text);
	}

}
