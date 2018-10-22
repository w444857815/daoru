package com.wangzhixuan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MyThread extends Thread {
	
	private String a ;
	
	public MyThread(String a){
		this.a = a;
	}
	
	public void run() {
		String url = "jdbc:mysql://127.0.0.1:3306/shiro";
		String name = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "root";
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
		
		System.out.println(a+"这里是获取到传进来的值，变吗看看");
		
		// 开始时间
		Long begin = new Date().getTime();
		// sql前缀
		String prefix = "INSERT INTO test (name) VALUES ";
		try {
			// 保存sql后缀
			StringBuffer suffix = new StringBuffer();
			// 设置事务为非自动提交
			conn.setAutoCommit(false);
			// 比起st，pst会更好些
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");// 准备执行语句
			// 外层循环，总提交事务次数
			for (int i = 1; i <= 10; i++) {
				suffix = new StringBuffer();
				// 第j次提交步长 100000
				for (int j = 1; j <= 10; j++) {
					// 构建SQL后缀
					suffix.append("('" + i * j + "'),");
				}
				// 构建完整SQL
				String sql = prefix + suffix.substring(0, suffix.length() - 1);
				// 添加执行SQL
				pst.addBatch(sql);
				System.out.println(sql);
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
	}
	
	public static void main(String[] args) {
		List<String> list100w = new LinkedList<>();
		//然后判断list的大小，根据大小来进行分割
		int size = 1000000;
		//判定分这么多个线程,同时，也创建这么多个参数 10个比如是
		int j = size%100000>0?(size/100000+1):size/100000;
		//List<String> list1 = new LinkedList<>();
		
		//不管有多少数据，都分10个线程
		for (int i = 1; i <=10; i++) {
			
			int k = (size/10)*i;
			
    		//new MyThread(i+"").start();
    		
		}
		//把80万数据分成10份
		List<Integer> list1 = new LinkedList<>();
		List<Integer> list2 = new LinkedList<>();
		List<Integer> list3 = new LinkedList<>();
		List<Integer> list4 = new LinkedList<>();
		
		//定义总条数
		int totalSize = 127;
		//定义线程数
		int threadNum = 3;
		//定义单元大小
		int unitSize = totalSize/threadNum ;
		
		
		System.out.println("单元大小是"+unitSize);
		
		for (int i = 0; i <threadNum; i++) {
			//这个优化
			int unitStart = unitSize*i;
			int unitEnd = unitSize*(i+1);
			for (int k = unitStart; k < unitEnd; k++) {
				System.out.println("分开所传的是"+k);
			}
			//这个就走1次，不用管
			if(i==(threadNum-1)){
				// 这个优化一下
				int unitStart_in = unitSize*(i+1);
				for (int k = unitStart_in; k <= totalSize; k++) {
					System.out.println("分开所传的是"+k);
				}
			}
			//上面的是处理数据的代码，下面是执行线程的代码
//    		new MyThread(i+"").start();
		}
		
		
		
		
		for (int i = 0; i < 13; i++) {
			if(0<i&&i<=1){
				list1.add(i);
				//这里执行线程
				continue;
			}
			if(1<i&&i<=2){
				list2.add(i);
				continue;
			}
			if(2<i&&i<=3){
				list3.add(i);
				continue;
			}
			// ---
			
			if(9<i&&i<=13){
				list3.add(i);
				continue;
			}
			
		}
		
		System.out.println(j);
		
		/*for (int i = 1; i <=10; i++) {
    		new MyThread(i+"").start();
		}*/
	}
}