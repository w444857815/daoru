package com.wangzhixuan.util.xiancheng;

public class MyRunThread implements Runnable {
	
	private int ticket;
	
	public MyRunThread(int ticket){
		this.ticket = ticket;
	}

	public void run() {
		while(this.ticket > 0){
			System.out.println("卖票：ticket" + this.ticket--);
		}
	}
}