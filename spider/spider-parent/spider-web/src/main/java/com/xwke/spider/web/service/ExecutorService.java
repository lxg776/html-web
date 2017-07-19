package com.xwke.spider.web.service;

import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataOperationVo;
import com.xwke.spider.vo.ExectorVo;

public interface ExecutorService {

	public void addExecutor(ExectorVo exectorVo);

	public void updateExecutor(ExectorVo exectorVo);

	public PageOnterModle getList(int pageNum);

	public ExectorVo getExecutorById(long id);

	public DataOperationVo getDataOperationById(long id);

	public void addDataOperation(DataOperationVo vo);
	
	public void updateDataOperation(DataOperationVo vo);
	
	public ExectorVo getExecutorAndDataOperationById(long id);

}
