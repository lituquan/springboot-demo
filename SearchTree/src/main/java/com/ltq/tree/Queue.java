package com.ltq.tree;


import java.util.LinkedList;
import java.util.List;

public class Queue {
    List<Node> nodesArr;
    int length;
    int max;
    public Queue(int max){
        this.max=max;
        nodesArr=new LinkedList<Node>();
    }
    public void add(Node node){
        if(length==max){
            System.out.println("queus is full");
            return;
        }
        nodesArr.add(node);
        length=nodesArr.size();
    }
    public Node get(){
        if(length<1){
            System.out.println("queus is empty");
            return null;
        }
        Node node=nodesArr.get(0);
        nodesArr=nodesArr.subList(1,nodesArr.size());
        length=nodesArr.size();
        return node;
    }

    public static void main(String[] args) {
        Queue q=new Queue(10);
        for (int i = 0; i < 10; i++) {
            q.add(new Node(i));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(q.get().data);
        }
    }
}
