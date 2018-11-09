package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.DbUserHeadertitle;



public interface DbUserHeadertitleMapper extends BaseMapper<DbUserHeadertitle> {
	
	/**
	 * 根据实体类条件查询
	* @Title: selectByModel 
	* @param model 实体类
	* @return    设定文件 
	* @return List<DbUserHeadertitle>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2018-11-07 10:48:37
	 */
	List<DbUserHeadertitle> selectByModel(DbUserHeadertitle model);
	
	/**
	 * 根据map条件查询
	* @Title: selectByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2018-11-07 10:48:37
	 */
	List<DbUserHeadertitle> selectByMap(Map<String,Object> map);
	
	/**
	 * 根据map条件修改
	* @Title: updateByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2018-11-07 10:48:37
	 */
	Integer updateByMap(Map<String,Object> map);
	
	/**
	 * 根据map条件删除
	* @Title: deleteByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2018-11-07 10:48:37
	 */
	Integer deleteByMap(Map<String,Object> map);
	/**
	 * 插入标题
	 * @Title:DbUserHeadertitleMapper.java
	 * @Package com.wangzhixuan.mapper
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2018年11月7日下午3:53:06
	 * @param headerTitlemap
	 * @version V1.0
	 */
	void insertHeaderTitle(Map<String, Object> headerTitlemap);
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
	void deleteHeaderTitle(Map<String, Object> headerTitlemap);
}
