package com.wangzhixuan.service;

import java.util.List;

import com.wangzhixuan.model.XsType;

public interface XsTypeFacade  extends BaseService<XsType> {

	List<XsType> selectAll();

	List<XsType> selectBatchIds(List<String> asList);

//	int updateByPrimaryKeySelective(XsType unitType);
	
	
}

