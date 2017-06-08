package com.xwke.spider.huntsman;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;

public class HuntressPipeLine extends FilePipeline {

	public HuntressPipeLine() {

	}

	public HuntressPipeLine(String path) {
		super(path);

	}
	
	@Override
	public void process(ResultItems resultItems, Task task) {

		System.out.println(resultItems);

		try {
			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(getFile(path + "88" + ".html")), "UTF-8"));
			Map<String, Object> results = resultItems.getAll();
			printWriter.println(results.get(DetailPipeLine.PARM_HTML));
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String args[]){
//		HuntressPipeLine ss= new HuntressPipeLine("+9+");
//		System.out.println(ss.getPath());
		
	}

}
