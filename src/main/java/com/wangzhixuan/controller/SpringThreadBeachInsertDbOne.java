package com.wangzhixuan.controller;

import java.util.Date;
import java.util.List;

import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.service.IDbTableoneService;
import com.wangzhixuan.util.BreakList;

public class SpringThreadBeachInsertDbOne extends Thread {
	
//	@Autowired
    private IDbTableoneService dbTableoneService;
    
	private List<DbTableone> dbTableOneList ;
	
	public SpringThreadBeachInsertDbOne(List<DbTableone> insertList){
		this.dbTableOneList = insertList;
	}
	
	public SpringThreadBeachInsertDbOne(IDbTableoneService dbTableoneService,List<DbTableone> insertList){
		this.dbTableoneService = dbTableoneService;
		this.dbTableOneList = insertList;
	}
	
	public void run() {
		//定义几个列表，也就是分几次事物事物
		
		int shiwuSize = 10;
		if(dbTableOneList.size()>shiwuSize){
			List<List<DbTableone>> numsList = BreakList.createListBySizeNum(dbTableOneList, shiwuSize);
			for (int i = 0; i < numsList.size(); i++) {
				// 开始时间
				Long begin = new Date().getTime();
				dbTableoneService.insertBatch(numsList.get(i));
				// 开始时间
				Long end = new Date().getTime();
				// 耗时
				System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
			}
		}else{
			// 开始时间
			Long begin = new Date().getTime();
			dbTableoneService.insertBatch(dbTableOneList);
			// 开始时间
			Long end = new Date().getTime();
			// 耗时
			System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
		}
		
	}
}