package com.wangzhixuan.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wangzhixuan.mapper.DbConfigMapper;
import com.wangzhixuan.mapper.DbTableoneMapper;
import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.service.IDbConfigService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
@Service
public class DbConfigServiceImpl extends ServiceImpl<DbConfigMapper, DbConfigTable> implements IDbConfigService {

	@Autowired
	private DbConfigMapper dbConfigMapper;
	
	
	@Override
	public int selectAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectExistNums(List<String> keylist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByIdCard(DbTableone dbTableone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBatchByIdCard(List<DbTableone> updateList) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 批量插入动态表
	 */
	@Override
	public void insertBatch(List<DbConfigTable> entityList, String tableName, LinkedList<Integer> colNumsList) {
		dbConfigMapper.insertBatch(entityList,tableName,colNumsList);
	}

	@Override
	public void truncateTable(String tableEnd) {
		dbConfigMapper.truncateTable(tableEnd);
	}

	@Override
	public List<Integer> selectEveryCount(String loginName) {
		List<Integer> list = dbConfigMapper.selectEveryCount(loginName);
		return list;
	}

	@Override
	public List<DbConfigTable> selectdbTablesData(Map<String, Object> csmap) {
		List<DbConfigTable> list = dbConfigMapper.selectdbTablesData(csmap);
		return list;
	}

	/**
	 * 按表头多少个查多少列
	 */
	@Override
	public List<DbConfigTable> selectExampleLimitData(Map<String, Object> csmap) {
		List<DbConfigTable> list = dbConfigMapper.selectExampleLimitData(csmap);
		return list;
	}
	
	
	
	
}
