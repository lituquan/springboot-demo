package com.ltq.tree;

public class Node {
    int data; // 数据域
    public Node right; // 右子树
    public Node left; // 左子树
    public Node(){

    }
    public Node(int data){
        this.data=data;
    }
    public void travel(){
        System.out.println(data);
        if (left!=null){
            left.travel();
        }
        if (right!=null){
            right.travel();
        }
    }
    public void travell(){
        if (left!=null){
            left.travell();
        }
        System.out.println(data);
        if (right!=null){
            right.travell();
        }
    }
    public void travelr(){
        if (right!=null){
            right.travelr();
        }
        System.out.println(data);
        if (left!=null){
            left.travelr();
        }
    }
    public void travelByLevel(){
        //root left 入队 right入队
        Queue queue=new Queue(100);
        queue.add(this);
        while (queue.length>0){
            Node node=queue.get();
            System.out.println(node.data);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }
    //插入、增加节点
    public void insert(int data){
        if(this.data==data){
            System.out.println("data exist:"+data);
        }else  if(this.data>data){
            //左边
            if(this.left==null){
                this.left=new Node(data);
                return;
            }
            this.left.insert(data);
        }else{
            //右边
            if(this.right==null){
                this.right=new Node(data);
                return;
            }
            this.right.insert(data);
        }
        return ;
    }
    public  int getLevel(){
        int l=0,r=0;
        if (left!=null){
            l=left.getLevel();
        }
        if (right!=null){
            r=right.getLevel();
        }
        return Math.max(l,r)+1;
    }
    public Node delete(int data){
        Node node=this;
        if(this==null){
            return null;
        }
        if(node.data==data){
            if(node.left==null && node.right==null){
                return null;
            }else if(node.left==null && node.right!=null){
                return node.right;
            }else if(node.left!=null && node.right==null){
                return node.left;
            }else{
                //rget(node);
                lget(node);
                return  node;
            }
        }else if(node.data<data){
            node.right=node.right.delete(data);
        }else{
            node.left=node.left.delete(data);
        }
        return this;
    }

    public void rget(Node node){
        //找右后继节点
        Node cur=node.right;
        while(cur.left!=null){
            cur=cur.left; //右边的最小值
        }
        //值替换
        node.data=cur.data;
        System.out.println("rmin after node:"+cur.data);
        //删除后继
        cur.delete(cur.data);
        return ;
    }

    public void lget(Node node){
        //找右后继节点
        Node cur=node.left;
        while(cur.right!=null){
            cur=cur.right; //右边的最小值
        }
        //值替换
        node.data=cur.data;
        System.out.println("lmax after node:"+cur.data);
        //删除后继
        cur.delete(cur.data);
        return ;
    }
}
