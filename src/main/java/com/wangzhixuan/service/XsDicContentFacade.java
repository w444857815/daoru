package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.XsDicContent;

public interface XsDicContentFacade extends BaseService<XsDicContent> {

	Integer deleteByMap(Map<String, Object> map);

	void insertBatchList(List<XsDicContent> mululist);

}

