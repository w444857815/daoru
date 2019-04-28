package com.wangzhixuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.XsTypeMapper;
import com.wangzhixuan.model.XsType;
import com.wangzhixuan.service.XsTypeFacade;


@Service("xsTypeFacade")
public class XsTypeFacadeImpl extends BaseServiceImpl< XsType,XsTypeMapper>	implements XsTypeFacade {
			
	@Autowired
	private XsTypeMapper xsTypeMapper;

	@Override
	public XsTypeMapper getMapper() {
		return xsTypeMapper;
	}

	@Override
	public List<XsType> selectBatchIds(List<String> asList) {
		List<XsType> list = xsTypeMapper.selectBatchIds(asList);
		return list;
	}

	/*@Override
	public List<XsType> selectAll() {
		List<XsType> list = xsTypeMapper.selectAll();
		return list;
	}*/

	/*@Override
	public int updateByPrimaryKeySelective(XsType unitType) {
		return xsTypeMapper.updateByPrimaryKeySelective(unitType);
	}*/
	
	
}
