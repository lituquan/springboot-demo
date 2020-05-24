package com.ltq.undertow.trie;

import java.util.HashMap;
import java.util.Map;

public class Tire {
	public String data;
	public boolean isEnd;
	public Map<String,  Tire> map;
	
	public Tire(String data) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.map=new HashMap<>();
	}
	//0-a-b-c
	//0-a-d-c
	public void create(String str,int index) {
		if(str.length()<index) {
			return;
		}
		String d=str.substring(0, index);
		if(!this.map.containsKey(d)) {			
			Tire tire=new Tire(d);
			this.map.put(d, tire);
			this.isEnd=(d.equals(str));
		}
		Tire aTire=this.map.get(d);
		aTire.create(str,index+1);
	}
	public  Tire search(String pre) {

		Tire root=this;
		for(int i=1;i<=pre.length();i++) {
			String key=pre.substring(0,i);
			if(root.map.containsKey(key)) {
				root=root.map.get(key);
				if(i==pre.length()) {
					return root;
				}
			}		
		}
		return null;
	}
	public  void show() {
		Tire root=this;
		
		System.out.println(this.data+","+this.isEnd);
		for(Tire k:root.map.values()) {			
			k.show();
		}
		if(root.map.size()==0) {
			return;
		}
	}
	public static void main(String[] args) {
		String [] the= {"abc","abd","123","abcnn"};// 0 - a -ab -abc
		Tire root=new Tire("");
		for (int i = 0; i < the.length; i++) {
			String str=the[i];
			root.create( str,1);
		}	
//		show(root);
		Tire search = root.search( "abc");
		search.show();		
	}
}
