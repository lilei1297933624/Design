package cn.lei.test;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Test {

    public TreeNode trimBST(TreeNode root,int L, int R){
        if(root == null)
            return null;
        if(root.val > R)
            return trimBST(root.left,L,R);
        if(root.val < L)
            return trimBST(root.right,L,R);
        root.left = trimBST(root.left,L,R);
        root.right = trimBST(root.right,L,R);
        return root;
    }

    public static void main(String[] args) {
        new Thread(new WaitingTime(),"WaitingTimeThread").start();
        new Thread(new WaitingState(),"WaitingStateThread").start();

        new Thread(new BlockedThread(),"BlockedThread-01").start();
        new Thread(new BlockedThread(),"BlockThread-02").start();
    }

}


class WaitingTime implements Runnable{

    @Override
    public void run() {
        while (true){
            try {
                waitSecond(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static final void waitSecond(long seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
}

class WaitingState implements Runnable{

    @Override
    public void run() {
        while (true){
            synchronized (WaitingState.class){
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class BlockedThread implements Runnable{

    @Override
    public void run() {
        synchronized (BlockedThread.class){
            while (true){
                try {
                    WaitingTime.waitSecond(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTree<T extends Comparable<T>>{
    private BSTree<T> mRoot;

    public class BSTNode<T extends Comparable<T>> {
        T key;                // 关键字(键值)
        BSTNode<T> left;      // 左孩子
        BSTNode<T> right;     // 右孩子
        BSTNode<T> parent;    // 父结点

        public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
