package com.wangzhixuan.util.xiancheng;

public class RunnableTicket {
	public static void main(String[] args) {
		MyRunThread mt = new MyRunThread(10000000);
		new Thread(mt).start();// 同一个mt，但是在Thread中就不可以，如果用同一
		new Thread(mt).start();// 个实例化对象mt，就会出现异常
		new Thread(mt).start();
		
	}
};