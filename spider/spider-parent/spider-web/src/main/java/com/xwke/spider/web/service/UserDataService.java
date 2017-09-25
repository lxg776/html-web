package com.xwke.spider.web.service;

import java.util.List;

import com.xwke.spider.modle.DataFieldModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataFieldVo;
import com.xwke.spider.vo.DataTableVo;

public interface UserDataService {

	public PageOnterModle getTableListByUserId(long userId, int page);

	public DataTableVo getDataTableById(long id);

	public DataFieldVo getDataFieldById(long id);

	public int saveDataTableVo(DataTableVo vo);

	public List<DataFieldModle> getFieldListByTid(long tid);

	public int saveDataFieldVo(DataFieldVo vo);

}
