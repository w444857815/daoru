package com.wangzhixuan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.XsDicContentMapper;
import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.service.XsDicContentFacade;


@Service("xsDicContentFacade")
public class XsDicContentFacadeImpl
		extends BaseServiceImpl<XsDicContent,XsDicContentMapper>
		implements XsDicContentFacade {
			
	@Autowired
	private XsDicContentMapper xsDicContentMapper;
	
	@Override
	public XsDicContentMapper getMapper(){
		return xsDicContentMapper;
	}

	@Override
	public Integer deleteByMap(Map<String, Object> map) {
		return xsDicContentMapper.deleteByMap(map);
	}

	@Override
	public void insertBatchList(List<XsDicContent> mululist) {
		xsDicContentMapper.insertBatchList(mululist);
	}
	
}
