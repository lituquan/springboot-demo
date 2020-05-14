package com.ltq.undertow.java8;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Java8Tester {
	public static void main(String args[]){
		new Thread(()->System.out.println(Thread.currentThread().getId()+" is working")).start();

		Runnable runnable=()->System.out.println(Thread.currentThread().getId()+" is working");
		new Thread(runnable).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId()+" is working");
			}
		}).start();

		//接口
		Operator add=(a,b)->a+b;
		Operator sub=(a,b)->a-b;
		Operator mul=(a,b)->a*b;
		Operator div=(a,b)->a+b;
		List<Operator> opertors=new LinkedList<Operator>();
		opertors.add(add);
		opertors.add(sub);
		opertors.add(mul);
		opertors.add(div);

		int a=10,b=5;
		//完成了opertors 到 double的转换
		opertors.stream().map((each)->each.oper(a, b)).forEach(System.out::println);
		List<Double> collect = opertors.stream().map((each)->each.oper(a, b)).collect(Collectors.toList());		  

		List<Double> res=new LinkedList<Double>();
		res.add(add.oper(a, b));
		res.add(sub.oper(a, b));
		res.add(mul.oper(a, b));
		res.add(div.oper(a, b));
		res.forEach(System.out::println);

		int w=10;
		Random random=new Random(System.currentTimeMillis());
		int bound=1000;
		w=random.nextInt(bound);
		Cat c1=new Cat(w);
		w=random.nextInt(bound);
		Cat c2=new Cat(w);
		w=random.nextInt(bound);
		Cat c3=new Cat(w);
		w=random.nextInt(bound);
		Cat c4=new Cat(w);		
		List<Cat> cats= new LinkedList<Cat>();
		cats.add(c1);
		cats.add(c2);
		cats.add(c3);
		cats.add(c4);
		Collections.sort(cats, (o1,o2)->o2.weight-o1.weight);
		cats.stream().forEach(System.out::println);

	}
	public void oper(Operator o) {
		System.out.println(o);
	}
}
interface Operator{
	double oper(int a,int b);
}
class Cat {
	int weight;
	public Cat(int w) {
		// TODO Auto-generated constructor stub
		weight=w;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return weight+"";
	}
}
