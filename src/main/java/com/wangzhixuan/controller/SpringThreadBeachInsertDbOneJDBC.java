package com.wangzhixuan.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.util.BreakList;
import com.wangzhixuan.util.reflectUtil;

public class SpringThreadBeachInsertDbOneJDBC extends Thread {
	
//	@Autowired
    private IDbConfigService iDbConfigService;
    
	
	//要导入的表名
	private String tableName;
	
	//列数
	private LinkedList<Integer> colNumsList;
	
	private List<DbConfigTable> dbTableOneList ;
	
	private List<String[]> shuzuData;
	
	private CountDownLatch count;
	
	
	public SpringThreadBeachInsertDbOneJDBC(IDbConfigService iDbConfigService,String tableName, LinkedList<Integer> colNumsList,List<DbConfigTable> dbTableOneList, CountDownLatch count){
		this.iDbConfigService = iDbConfigService;
		this.tableName = tableName;
		this.colNumsList = colNumsList;
		this.dbTableOneList = dbTableOneList;
		this.count = count;
	}
	

	public void run() {
		String url = "jdbc:mysql://127.0.0.1:3306/shiro?&useSSL=true";
		String name = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "qweABC123.";
		Connection conn = null;
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			conn.setAutoCommit(false);// 关闭自动提交，不然conn.commit()运行到这句会报错
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 开始时间
		Long begin = new Date().getTime();
		// sql前缀
		String prefix = "INSERT INTO "+tableName+" ";  
		try {
			
			// 保存sql后缀
			StringBuffer suffix = new StringBuffer();
			// 设置事务为非自动提交
			conn.setAutoCommit(false);
			// 比起st，pst会更好些
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");// 准备执行语句
			//字段
			String colName = "";
			for (int i = 0; i < colNumsList.size(); i++) {
				colName+="col"+i + ",";
			}
			if(colName.length()>0){
				colName = colName.substring(0, colName.length()-1);
			}
			colName = "("+colName+")";
			// 构建完整SQL
			
			// 外层循环，总提交事务次数
			/*for (int i = 1; i <= 10; i++) {
				suffix = new StringBuffer();
				
				String colValue = "";
				int index_yb = 0;
				for (int j = index_yb; j < shuzuData.size(); j++) {
					String[] colVals = shuzuData.get(j);
					colValue += colVals[i]+",";
					index_yb = j;
				}
				if(colValue.length()>0){
					colValue = colValue.substring(0, colValue.length()-1);
				}
				colValue = "("+colValue+")";
				// 第j次提交步长 100000
				for (int j = 1; j <= 10; j++) {
					// 构建SQL后缀
					suffix.append("('" + i * j + "'),");
				}
				// 构建完整SQL
				String sql = prefix + colName ;
				// 添加执行SQL
				pst.addBatch(sql);
				System.out.println(sql);
				// 执行操作
				pst.executeBatch();
				// 提交事务
				conn.commit();
				// 清空上一次添加的数据
				suffix = new StringBuffer();
			}*/
			
			//每个放10000个，不知道分几个
			List<List<DbConfigTable>> everylist = BreakList.createListByUnitSize(dbTableOneList, 10000);
			System.out.println("分成了"+everylist.size()+"个");
			for (int i = 0; i < everylist.size(); i++) {
				String sql = prefix + colName + " VALUES ";
				suffix = new StringBuffer();
				
				
				String colValueAll = "";
				for (int j = 0; j < everylist.get(i).size(); j++) {
//					String[] colVals = shuzuData.get(j);
//					colValue += colVals[i]+",";
					String colValue = "'";
					for (int k = 0; k < colNumsList.size(); k++) {
						colValue+=reflectUtil.reflectget(everylist.get(i).get(j), "col"+k)+"','";
					}
					if(colValue.length()>0){
						colValue = colValue.substring(0, colValue.length()-2);
					}
					colValue = "("+colValue+")";
					colValueAll +=colValue+",";
				}
				colValueAll = colValueAll.substring(0,colValueAll.length()-1);
				
				
				// 第j次提交步长 100000
//				for (int j = 1; j <= 10; j++) {
//					// 构建SQL后缀
//					suffix.append("('" + i * j + "'),");
//				}
				// 构建完整SQL
				sql = sql + colValueAll ;
				// 添加执行SQL
				pst.addBatch(sql);
//				System.out.println(sql);
				System.out.println("提交第"+i+"次");
				sql = "";
				// 执行操作
				pst.executeBatch();
				// 提交事务
				conn.commit();
				// 清空上一次添加的数据
				suffix = new StringBuffer();
			}
			
			// 头等连接
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 结束时间
		Long end = new Date().getTime();
		// 耗时
		System.out.println("100万条数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
		count.countDown();
	}
	
	public static void main(String[] args) {
		
		/*for (int i = 1; i <=10; i++) {
    		new MyThread(i+"").start();
		}*/
	}
}