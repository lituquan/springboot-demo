package com.ltq.undertow.trie;

import java.util.HashMap;
import java.util.Map;

public class Tire {
	public String data;
	public Map<String,  Tire> map;
	
	public Tire(String data) {
		// TODO Auto-generated constructor stub
		this.data=data;
		this.map=new HashMap<>();
	}
	//0-a-b-c
	//0-a-d-c
	public static void create(Tire root,String str,int index) {
		if(str.length()<index) {
			return;
		}
		String d=str.substring(0, index);
		if(!root.map.containsKey(d)) {			
			Tire tire=new Tire(d);
			root.map.put(d, tire);	 
		}
		create(root.map.get(d),str,index+1);
	}
	public static Tire search(Tire root,String pre) {
		System.out.println(root.data);

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
	public static void show(Tire root) {
		System.out.println(root.data);
		for(Tire k:root.map.values()) {			
			show(k);
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
			Tire.create(root, str,1);
		}	
//		show(root);
		Tire search = Tire.search(root, "ab");
		Tire.show(search);		
	}
}
