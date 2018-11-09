package com.wangzhixuan.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.mapper.DbUserHeadertitleMapper;
import com.wangzhixuan.model.DbUserHeadertitle;
import com.wangzhixuan.service.DbUserHeadertitleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
@Service
public class DbUserHeadertitleServiceImpl extends ServiceImpl<DbUserHeadertitleMapper, DbUserHeadertitle> implements DbUserHeadertitleService {

	@Autowired
	private DbUserHeadertitleMapper dbUserHeadertitleMapper;

	@Override
	public void insertHeaderTitle(Map<String, Object> headerTitlemap) {
		dbUserHeadertitleMapper.insertHeaderTitle(headerTitlemap);
	}
	/**
	 * 先删了表头，然后再插入
	 * @Title:DbUserHeadertitleService.java
	 * @Package com.wangzhixuan.service
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2018年11月7日下午4:12:29
	 * @param headerTitlemap
	 * @version V1.0
	 */
	@Override
	public void deleteHeaderTitle(Map<String, Object> headerTitlemap) {
		dbUserHeadertitleMapper.deleteHeaderTitle(headerTitlemap);
	}
	
	
	
}
