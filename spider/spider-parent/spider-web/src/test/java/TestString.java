
import org.junit.Test;

import com.xwke.api.tenant.TenantDTO;
import com.xwke.api.tenant.TenantHelper;
import com.xwke.spider.huntsman.util.HtmlUtil;

public class TestString {
	@Test
	public void test02() {
		
		TenantDTO ss =new TenantDTO();
		TenantHelper.setTenantDto(ss);
		TenantHelper.getTenantDto().setRef("001");

		System.out.println("123123" + TenantHelper.getTenantDto().getRef());
	}

}
