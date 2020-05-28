package com.ltq.tree;

/*
*
1 2 9 7 8 5 6
构造二叉搜索树：增加节点只会在叶子节点增加
		1
			2
			   9
			7
		5		8
			6
删除节点：
    没有左右：直接删除
    只有一支：对应替换
    删除双支节点：左边最大替换，右边最小替换

中序
1 2 9 7 5 6 8
前序
1 2 5 6 7 8 9
后序
9 8 7 6 5 2 1
层次遍历
1进

1出
2进
1

2出
9进
1 2

9出
7进
1 2 9

7出
5、8进
1 2 9 7

5出
8、6进
1 2 9 7 5

8出
1 2 9 7 5 8

6出
1 2 9 7 5 8 6
*/
public class Tree {
    public Node root;//假设左节点>右节点
    public Tree(){
    }
    public Tree(Node node){
        this.root=node;
    }
    //插入、增加节点
    public void insert(int data){
        Node target=new Node(data);
        //树为空时候
        if(root==null){
            this.root=target;
            return ;
        }
        root.insert(data);
        return ;
    }
    //查找节点
    public Node search(int data){
        //树不为空时候
        if(root==null){
            return null;
        }
        Node cur=root;
        while(cur.data!=data){
            if(data>cur.data){
                cur=cur.right;
            }else{
                cur=cur.left;
            }
            if(cur==null){
                return null;
            }
        }
        //比较当前节点：=返回;data>cur 右边查找 ;data < cur 左边查找
        return cur;
    }
    //删除节点
    public void delete(int data){
        root=root.delete(data);
    }

    public static void main(String[] args) {
        Tree tree=new Tree();
        int[] intArr=new int[]{1,2,9,7,8,5,6};
        for (int i = 0; i < intArr.length; i++) {
            tree.insert(intArr[i]);
        }
        tree.delete(7);
        tree.root.travel();
        System.out.println("==========");
        tree.root.travell();
        System.out.println("==========");
        tree.root.travelr();
        System.out.println("==========");
        tree.root.travelByLevel();
        System.out.println("==========");
        int level=tree.root.getLevel();
        System.out.println("level:"+level);

    }
}
