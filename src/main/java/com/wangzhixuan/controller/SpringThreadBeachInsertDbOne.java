package com.wangzhixuan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.util.BreakList;

public class SpringThreadBeachInsertDbOne extends Thread {
	
//	@Autowired
    private IDbConfigService iDbConfigService;
    
	private List<DbConfigTable> dbTableOneList ;
	
	//要导入的表名
	private String tableName;
	
	//列数
	private LinkedList<Integer> colNumsList;
	
	private CountDownLatch count;
	
	public SpringThreadBeachInsertDbOne(List<DbConfigTable> insertList){
		this.dbTableOneList = insertList;
	}
	
	public SpringThreadBeachInsertDbOne(IDbConfigService iDbConfigService,List<DbConfigTable> insertList,String tableName, LinkedList<Integer> colNumsList, CountDownLatch count){
		this.iDbConfigService = iDbConfigService;
		this.dbTableOneList = insertList;
		this.tableName = tableName;
		this.colNumsList = colNumsList;
		this.count = count;
	}
	
	public void run() {
		//这里定义的是每个里面放多少个，比如有1000个，这里是200，那就是分5次来插入
		int shiwuSize = 100;
		if(dbTableOneList.size()>shiwuSize){
			//不管里面有多少，都分shiwuSize个
//			List<List<DbConfigTable>> numsList = BreakList.createListBySizeNum(dbTableOneList, shiwuSize);
			//按每个里面有多少来分，比如有100个，这里的数字是5，那就是分20个
//			List<List<DbConfigTable>> numsList = BreakList.createListByUnitSize(dbTableOneList, shiwuSize);
			List<List<DbConfigTable>> numsList = new ArrayList<List<DbConfigTable>>() ;
			for (int i = 0; i < numsList.size(); i++) {
				// 开始时间
				Long begin = new Date().getTime();
				iDbConfigService.insertBatch(numsList.get(i),tableName,colNumsList);
				// 开始时间
				Long end = new Date().getTime();
				// 耗时
				System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
			}
		}else{
			// 开始时间
			Long begin = new Date().getTime();
			iDbConfigService.insertBatch(dbTableOneList,tableName,colNumsList);
			// 开始时间
			Long end = new Date().getTime();
			// 耗时
			System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
		}
		count.countDown();
	}
}