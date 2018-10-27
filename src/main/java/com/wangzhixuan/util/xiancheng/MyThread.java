package com.wangzhixuan.util.xiancheng;

public class MyThread extends Thread {
	
	private int a ;
	
	public MyThread(int a){
		this.a = a;
	}
	
	public void run() {
		while(this.a > 0){
			System.out.println("卖票：ticket" + this.a--);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 1; i <=3; i++) {
    		new MyThread(10000000).start();
		}
	}
}