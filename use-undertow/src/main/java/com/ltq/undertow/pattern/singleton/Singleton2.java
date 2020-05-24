package com.ltq.undertow.pattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton2 {
	//维护一个对象:懒汉式
	private static Singleton2 instance=null;
	//避免被调用
	public AtomicInteger integer=new AtomicInteger();
	private Singleton2() {
		integer.incrementAndGet();		
	}
	//暴露：缺点，性能低
	public static synchronized Singleton2 getInstance() {
		if(instance==null) {
			instance=new Singleton2();
		}
		return instance;
	}
}
