package com.ltq.undertow.blockqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.IntConsumer;


public class ZeroEvenOdd {
    private int n;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    public static BlockingDeque<Integer> q0 = new LinkedBlockingDeque(1);
        public static BlockingDeque<Integer> q1 = new LinkedBlockingDeque(1);
            public static BlockingDeque<Integer> q2 = new LinkedBlockingDeque(1);
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            printNumber.accept(0);
            if((i&1)==1){
                q1.put(i);
            }else{
                q2.put(i);
            }
            q0.take();
        }
        q1.put(-1);
        q2.put(-1);
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(;;){
            int result=q2.take();
            if(result==-1){
            	break;
            }
            printNumber.accept(result);
            q0.put(result);                   
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
         for(;;){
            int result=q1.take();
            if(result==-1){
            	break;
            }
            printNumber.accept(result);
            q0.put(result);
        }
    }
    public static void main(String[] args) {
    	ZeroEvenOdd z=new ZeroEvenOdd(1);    	
		Thread t1=new ZeroThread(z);
		Thread t2=new EventThread(z);
		Thread t3=new OddThread(z);
		t1.start();
		t2.start();
		t3.start();
	}
    
}

class ZeroThread extends Thread{
	ZeroEvenOdd zeo;
	IntConsumer printNumber = new IntConsumer(){
		@Override
		public void accept(int value) {
			// TODO Auto-generated method stub
			System.out.println(value);
		}
	};
	public ZeroThread(ZeroEvenOdd zeo){
		this.zeo=zeo;
	}
	@Override
	public void run() {
		try {
			zeo.zero(printNumber);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class EventThread extends Thread{
	ZeroEvenOdd zeo;
	IntConsumer printNumber = new IntConsumer(){
		@Override
		public void accept(int value) {
			// TODO Auto-generated method stub
			System.out.println(value);
		}
	};
	public EventThread(ZeroEvenOdd zeo){
		this.zeo=zeo;
	}
	@Override
	public void run() {
		try {
			zeo.even(printNumber);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class OddThread extends Thread{
	ZeroEvenOdd zeo;
	IntConsumer printNumber = new IntConsumer(){

		@Override
		public void accept(int value) {
			// TODO Auto-generated method stub
			System.out.println(value);
		}
	};
	public OddThread(ZeroEvenOdd zeo){
		this.zeo=zeo;
	}
	@Override
	public void run() {
		try {
			zeo.odd(printNumber);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}