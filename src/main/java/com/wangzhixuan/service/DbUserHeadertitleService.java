package com.wangzhixuan.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wangzhixuan.model.DbUserHeadertitle;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
public interface DbUserHeadertitleService extends IService<DbUserHeadertitle> {
	/**
	 * 插入标题
	 * @Title:DbUserHeadertitleService.java
	 * @Package com.wangzhixuan.service
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2018年11月7日下午3:52:38
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
