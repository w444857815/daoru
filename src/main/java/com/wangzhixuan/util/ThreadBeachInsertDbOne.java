package com.wangzhixuan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.service.IDbTableoneService;

@Controller
public class ThreadBeachInsertDbOne extends Thread {
	
	@Autowired
    private IDbTableoneService dbTableoneService;
    
	private List<DbTableone> dbTableOneList ;
	
	public ThreadBeachInsertDbOne(List<DbTableone> insertList){
		this.dbTableOneList = insertList;
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
		
		
		// 开始时间
		Long begin = new Date().getTime();
		// sql前缀
		String prefix = "insert into db_tableone (card_num, id_card, name, \r\n" + 
				"	      birthday, address, sex, \r\n" + 
				"	      minzu, rysx, country, \r\n" + 
				"	      country_cun, cbzt" + 
				"	      )\r\n" + 
				"	    values  ";
		try {
			// 保存sql后缀
			StringBuffer suffix = new StringBuffer();
			// 设置事务为非自动提交
			conn.setAutoCommit(false);
			// 比起st，pst会更好些
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");// 准备执行语句
			// 外层循环，总提交事务次数
			for (int i = 1; i <= 1; i++) {
				suffix = new StringBuffer();
				// 第j次提交步长 100000
				int size = dbTableOneList.size();
				for (int j = 0; j < size; j++) {
					// 构建SQL后缀
					suffix.append("('"+ dbTableOneList.get(j).getCardNum() + "','"+dbTableOneList.get(j).getIdCard()+
							"','"+dbTableOneList.get(j).getName()+"','"+dbTableOneList.get(j).getBirthday()+"','"
							+dbTableOneList.get(j).getAddress()+"','"+dbTableOneList.get(j).getSex()
							+"','"+dbTableOneList.get(j).getMinzu()+"','"+dbTableOneList.get(j).getRysx()
							+"','"+dbTableOneList.get(j).getCountry()+"','"+dbTableOneList.get(j).getCountryCun()
							+"','"+dbTableOneList.get(j).getCbzt()+"'"
							+ "),");
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
	
}