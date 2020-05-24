package com.ltq.undertow.pattern.singleton;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton3 {
	//维护对象
	private static volatile Singleton3 instance;//volatile  避免指令重排
	//私有化构造函数
	public AtomicInteger integer=new AtomicInteger();
	private Singleton3() {
		integer.incrementAndGet();		
	}
	//暴露对象
	public static Singleton3 getInstance() {
		if(instance==null) {
			synchronized (Singleton3.class) {
				if(instance==null) {
					instance=new Singleton3();
				}
			}
		}
		return instance;
	}
}
