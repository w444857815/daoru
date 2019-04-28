package com.wangzhixuan.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test1 {
	static int num = 0;
	public static void main(String[] args) throws SQLException {
//		test1();
		test2();
	}

	private static void test2() {
		int lianzaiSize = 121;
		int pageSize = lianzaiSize%20==0?lianzaiSize/20:(lianzaiSize/20+1);
		System.out.println(pageSize);
	}

	private static void test1() throws SQLException {
		
//		String proName = "admin";
//		String path = "E:/workspace/xxcx_admin/src/main/java";
		
		
//		String proName = "api";
//		String path = "E:/workspace/xxcx_api/src/main/java/com";
//		
//		
		String proName = "base";
		String path = "E:/workspace/xxcx_base/src/main/java";
//		
//		
		proName = "cache";
		path = "E:/workspace/xxcx_cache/src/main/java";
//		
//		
		proName = "common";
		path = "E:/workspace/xxcx_common/src/main/java";
//		
//		
		proName = "driver";
		path = "E:/workspace/xxcx_driver/src/main/java";
//		
//		
		proName = "location";
		path = "E:/workspace/xxcx_location/src/main/java";
//		
		proName = "order";
		path = "E:/workspace/xxcx_order/src/main/java";
//		
		proName = "passenger";
		path = "E:/workspace/xxcx_passenger/src/main/java";
//		
		proName = "PipePlatform";
		path = "E:/workspace/xxcx_PipePlatform/src/main/java";
//		
//		
		proName = "push";
		path = "E:/workspace/xxcx_push/src/main/java";
		
		
		proName = "schedule";
		path = "E:/workspace/xxcx_schedule/src/main/java";
//		
//		
		proName = "system";
		path = "E:/workspace/xxcx_system/src/main/java";
		
		
		
		
		
		
		
		File file = new File(path);
		System.out.println("开始");
		chazhao(proName,file);
		System.out.println("结束");
	}

	private static void chazhao( String proName, File file) throws SQLException {
		if(file.exists()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File childfile = files[i];
				//如果是文件夹，继续找
				if(childfile.isDirectory()){
					if(childfile.getName().endsWith(".svn")){
						continue;
					}
					chazhao(proName,childfile);
				}
				//如果不是，就列出下面的文件来
				else{
					
					String url = "jdbc:mysql://127.0.0.1:3306/shiro?allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&useSSL=false";
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
					
					String sql = "INSERT INTO `all_class` (`pro_name`, `allpath`, `class_name`) VALUES ('"+proName+"', '"+childfile.getPath().replace("\\", ".")+"', '"+childfile.getName()+"');";
					pst.addBatch(sql);
					// 执行操作
					pst.executeBatch();
					// 提交事务
					conn.commit();
					// 头等连接
					pst.close();
					conn.close();
					num++;
					System.out.println(num);
//					System.out.println(childfile.getPath()+"*"+childfile.getName());
//					System.out.println(childfile.getPath());
//					System.out.println(childfile.getName());
					
				}
			}
		}
	}
}
