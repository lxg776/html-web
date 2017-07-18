package com.xwke.spider.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.DataOperationDao;
import com.xwke.spider.dao.ExecutorDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.DataOperationModle;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataOperationVo;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;

@Service
public class ExecutorServiceImpl implements ExecutorService {

	@Resource
	ExecutorDao executorDao;
	@Resource
	DataOperationDao dataOperationDao;

	int pageSize = 10;

	@Override
	public void addExecutor(ExectorVo exectorVo) {
		// TODO Auto-generated method stub
		ExecutorModle executorModle = exectorVo.getTargetObject(ExecutorModle.class);
		executorDao.addLocal(executorModle);

	}

	@Override
	public void updateExecutor(ExectorVo exectorVo) {
		// TODO Auto-generated method stub
		ExecutorModle executorModle = exectorVo.getTargetObject(ExecutorModle.class);
		executorDao.update(executorModle);
	}

	@Override
	public PageOnterModle getList(int pageNum) {
		// TODO Auto-generated method stub

		Page<ExecutorModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<ExecutorModle> list = executorDao.list(new WherePrams(null, null, null));
		return CommonUtil.getPageOnter(pageonter);

	}

	@Override
	public ExectorVo getExecutorById(long id) {
		// TODO Auto-generated method stub
		ExecutorModle executorModle = executorDao.get(id);
		ExectorVo vo = executorModle.getTargetObject(ExectorVo.class);

		return vo;

	}

	@Override
	public DataOperationVo getDataOperationById(long id) {
		// TODO Auto-generated method stub
		DataOperationModle modle = dataOperationDao.get(id);
		DataOperationVo vo = modle.getTargetObject(DataOperationVo.class);
		return vo;
	}

}
