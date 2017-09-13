package com.xwke.spider.web.service;

import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataTableVo;

public interface UserDataService {

	public PageOnterModle getTableListByUserId(long userId, int page);

	public DataTableVo getDataTableById(long id);

	public int saveDataTableVo(DataTableVo vo);

}
