package com.wangzhixuan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.service.IDbTableoneService;

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
	
	public static void runa() {
		//设置每1000条提交一次
		//定义总条数
		int totalSize = 12;//dbTableOneList.size()
		//定义单元大小
		int unitSize = 10 ;
		//提交次数  2
		int commitSize = totalSize/unitSize>0?totalSize/unitSize+1:totalSize/unitSize;
		System.out.println(commitSize);
		for (int i = 0; i <commitSize; i++) {
			//这个优化
			int unitStart = unitSize*i;
			int unitEnd = unitSize*(i+1);
			for (int k = unitStart; k < unitEnd; k++) {
				System.out.println("分开所传的是"+k);
			}
			//这个就走1次，不用管
			if(i==(commitSize-1)){
				// 这个优化一下
				int unitStart_in = unitSize*(i+1);
				for (int k = unitStart_in; k < totalSize; k++) {
					System.out.println("分开所传的是"+k);
				}
			}
			//上面的是处理数据的代码，下面是执行线程的代码
//    		new MyThread(i+"").start();
		}
		//dbTableoneService.insertBatch(dbTableOneList);
	}
	public static void main(String[] args) {
		runa();
	}
}