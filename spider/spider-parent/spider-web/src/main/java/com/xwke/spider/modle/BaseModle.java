package com.xwke.spider.modle;

import com.dexcoder.commons.bean.BeanConverter;
import com.dexcoder.commons.exceptions.CommonsAssistantException;
import com.xwke.base.core.beans.Po;

public class BaseModle extends Po {

	/**
	 * 获取自动转换后的JavaBean对象
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T getTargetObject(Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			return BeanConverter.convert(t, this);
		} catch (Exception e) {
			throw new CommonsAssistantException("转换对象失败", e);
		}
	}

}
