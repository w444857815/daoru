package com.wangzhixuan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static final String YMD = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String DateToStrYMD(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
