package com.ltq.undertow.pattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton1 {
	//持有一个实例：饿汉式
	private static Singleton1 instance=new Singleton1();
	//避免被调用
	public AtomicInteger integer=new AtomicInteger();
	private Singleton1() {
		integer.incrementAndGet();		
	}
	//暴露对象
	public static Singleton1 getInstace() {
		return instance;
	}
}
