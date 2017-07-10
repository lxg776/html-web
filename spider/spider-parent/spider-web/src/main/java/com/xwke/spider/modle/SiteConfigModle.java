package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;


/**
 * 网站抓取相关配置信息
 * 
 * @author Administrator
 *
 */
@TableName(name = "s_site_config")
public class SiteConfigModle extends BaseModle{

	private Long id;

	@FieldName(name = "c_jsontext")
	private String configJsonText;
	@FieldName(name = "c_alias")
	private String alias;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfigJsonText() {
		return configJsonText;
	}

	public void setConfigJsonText(String configJsonText) {
		this.configJsonText = configJsonText;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
