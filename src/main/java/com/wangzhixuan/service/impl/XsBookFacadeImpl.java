package com.wangzhixuan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.XsBookMapper;
import com.wangzhixuan.model.XsBook;
import com.wangzhixuan.service.XsBookFacade;


@Service("xsBookFacade")
public class XsBookFacadeImpl extends BaseServiceImpl<XsBook,XsBookMapper> implements XsBookFacade {
			
	@Autowired
	private XsBookMapper xsBookMapper;
	
	@Override
	public XsBookMapper getMapper() {
		return xsBookMapper;
	}

	@Override
	public void insertBatchList(List<XsBook> endbooks) {
		xsBookMapper.insertBatchList(endbooks);
	}

	@Override
	public void deleteByMap(Map<String, Object> map) {
		xsBookMapper.deleteByMap(map);
	}

	@Override
	public List<XsBook> selectBatchIds(List<String> asList) {
		List<XsBook> list = xsBookMapper.selectBatchIds(asList);
		return list;
	}

	
	
}
