package com.xwke.spider.huntsman.configuration;

public class JxGovConfiguration extends BaseConfiguration {

	public static final String SUBDIR_NEWS = "news/";

	public String getNewsFolder(){
		 return getBaseDir()+SUBDIR_NEWS;
	 }

	public static void main(String[] args) {
		JxGovConfiguration configuration = new JxGovConfiguration();
		System.out.println(configuration.getSite());
		System.out.println(configuration.getBaseDir());
		

	}

}
