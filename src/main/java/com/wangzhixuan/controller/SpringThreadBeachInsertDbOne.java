package com.wangzhixuan.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.service.IDbTableoneService;
import com.wangzhixuan.util.BreakList;

public class SpringThreadBeachInsertDbOne extends Thread {
	
//	@Autowired
    private IDbConfigService iDbConfigService;
    
	private List<DbConfigTable> dbTableOneList ;
	
	//要导入的表名
	private String tableName;
	
	//列数
	private LinkedList<Integer> colNumsList;
	
	public SpringThreadBeachInsertDbOne(List<DbConfigTable> insertList){
		this.dbTableOneList = insertList;
	}
	
	public SpringThreadBeachInsertDbOne(IDbConfigService iDbConfigService,List<DbConfigTable> insertList,String tableName, LinkedList<Integer> colNumsList){
		this.iDbConfigService = iDbConfigService;
		this.dbTableOneList = insertList;
		this.tableName = tableName;
		this.colNumsList = colNumsList;
	}
	
	public void run() {
		//定义几个列表，也就是分几次事物事物
		
		int shiwuSize = 10;
		if(dbTableOneList.size()>shiwuSize){
			List<List<DbConfigTable>> numsList = BreakList.createListBySizeNum(dbTableOneList, shiwuSize);
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
		
	}
}