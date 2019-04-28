package com.wangzhixuan.util.pachong.axdzs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcConnect {
	
	public static void executeSql(String sql) throws SQLException{
		String url = "jdbc:mysql://127.0.0.1:3306/xs?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false";
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
		
		PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");
		//sql = "INSERT INTO `all_class` (`pro_name`, `allpath`, `class_name`) VALUES ('"+proName+"', '"+childfile.getPath().replace("\\", ".")+"', '"+childfile.getName()+"');";
		pst.addBatch(sql);
		// 执行操作
		pst.executeBatch();
		// 提交事务
		conn.commit();
		// 头等连接
		pst.close();
		conn.close();
	}
	
	
}
