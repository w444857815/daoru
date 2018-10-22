package com.wangzhixuan.service.impl;

import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.mapper.DbTableoneMapper;
import com.wangzhixuan.mapper.OrganizationMapper;
import com.wangzhixuan.service.IDbTableoneService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
@Service
public class DbTableoneServiceImpl extends ServiceImpl<DbTableoneMapper, DbTableone> implements IDbTableoneService {
	@Autowired
	private DbTableoneMapper dbTableoneMapper;
	
	@Override
	public int selectAllCount() {
		int i = dbTableoneMapper.selectAllCount();
		return i;
	}
	/**
	 * 执行sql查询，查出来里面有多少条重复
	 */
	@Override
	public List<String> selectExistNums(List<String> keylist) {
		List<String> list = dbTableoneMapper.selectExistNums(keylist);
		return list;
	}
	
	/**
	 * 更新数据
	 */
	@Override
	public int updateByIdCard(DbTableone dbTableone) {
		int i = dbTableoneMapper.updateByIdCard(dbTableone);
		return i;
	}
	/**
	 * 批量更新
	 */
	@Override
	public int updateBatchByIdCard(List<DbTableone> updateList) {
		int i = dbTableoneMapper.updateBatchByIdCard(updateList);
		return i;
	}
	
}
