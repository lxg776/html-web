package com.xwke.spider.web.service;

import java.util.List;

import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.modle.PreviewDataModle;
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

	public void delDataOperation(int exectorId, int operationId);

	public void savePreviewData(PreviewDataModle modle);

	public PreviewDataModle getModleByExecutorIdAndType(long executorId, String type);

	public List<ExecutorModle> getAllList();

	//public int addOrUpdatePreviewData(PreviewDataModle modle);

}
