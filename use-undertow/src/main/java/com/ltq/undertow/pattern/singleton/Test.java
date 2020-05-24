package com.ltq.undertow.pattern.singleton;

//https://www.cnblogs.com/ngy0217/p/9006716.html
public class Test {
	public static void main(String[] args) {
		int max=1000;
		for(int i=0;i<max;i++) {			
			Thread thread=new Thread(()->System.out.println(Singleton2.getInstance().integer));
			thread.start();
		}
		
	}
}
