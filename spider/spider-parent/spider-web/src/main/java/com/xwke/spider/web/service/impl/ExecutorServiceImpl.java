package com.xwke.spider.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
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

	/**
	 * 添加操作
	 */
	@Override
	public void addDataOperation(DataOperationVo vo) {
		// TODO Auto-generated method stub
		DataOperationModle modle = vo.getTargetObject(DataOperationModle.class);
		dataOperationDao.addLocal(modle);
		dataOperationDao.addRelByOperationAndExector(vo.getExecutorId(), modle.getId());

	}

	@Override
	public void delDataOperation(int exectorId, int operationId) {
		// TODO Auto-generated method stub
		dataOperationDao.del(operationId);
		dataOperationDao.delRelByOperationAndExector(exectorId, operationId);

	}

	@Override
	public void updateDataOperation(DataOperationVo vo) {
		// TODO Auto-generated method stub
		DataOperationModle modle = vo.getTargetObject(DataOperationModle.class);
		dataOperationDao.update(modle);
	}

	@Override
	public ExectorVo getExecutorAndDataOperationById(long id) {
		ExecutorModle executorModle = executorDao.get(id);
		ExectorVo vo = executorModle.getTargetObject(ExectorVo.class);

		String sql = String.format(
				"select o.id,o.weight,o.file_name as fileName,o.o_type as type , o.param1,o.param2, o.param3, o.param4, o.param5, o.r_type as rtype from s_data_operation as o join (select executor_id,operation_id from s_executor_operation_rel where executor_id =%d) as r on o.id=r.operation_id order by o.weight desc;",
				id);
		List<Map<String, Object>> allMapList = executorDao.listBySql(sql);
		Map<String, List<DataOperationVo>> operationMap = new HashMap();

		if (null != allMapList && allMapList.size() > 0) {
			for (Map itemMap : allMapList) {
				DataOperationVo data = CommonUtil.injectBean(DataOperationVo.class, itemMap);
				String fileName = data.getFileName();
				if (StringUtil.isNotEmpty(fileName)) {
					List<DataOperationVo> operationList;
					if (operationMap.containsKey(fileName)) {
						operationList = operationMap.get(fileName);
					} else {
						operationList = new ArrayList<>();
					}
					operationList.add(data);
					operationMap.put(fileName, operationList);
				}
			}
		}
		vo.setOperationMap(operationMap);
		// TODO Auto-generated method stub
		return vo;
	}

}
