package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.model.XsBook;

public interface XsBookFacade extends BaseService<XsBook> {
	/**
	 * 批量插入
	 * @Title:XsBookFacade.java
	 * @Package com.wangzhixuan.service
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2019年1月25日下午4:14:19
	 * @param endbooks
	 * @version V1.0
	 */
	void insertBatchList(List<XsBook> endbooks);

	void deleteByMap(Map<String, Object> map);

	List<XsBook> selectBatchIds(List<String> asList);

	
}

