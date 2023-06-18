package com.lyx.batch.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 */
public class Main {

    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        LeafNode l1 = new LeafNode("l1");
        LeafNode l2 = new LeafNode("l2");
        BranchNode selection21 = new BranchNode("selection21");
        LeafNode c211 = new LeafNode("c211");
        LeafNode c212 = new LeafNode("c212");
        root.addNode(chapter1);
        root.addNode(chapter2);
        chapter1.addNode(l1);
        chapter1.addNode(l2);
        chapter2.addNode(selection21);
        selection21.addNode(c211);
        selection21.addNode(c212);
        //打印树形层次
        tree(root,0);
    }

    static void tree(Node b,int depth){
        for(int i = 0 ; i < depth ; i++){
            System.out.print("--");
        }
        b.p();
        if(b instanceof BranchNode){
            for(Node node : ((BranchNode)b).nodes){
                tree(node,depth+1);
            }
        }
    }
}


abstract class Node{
    abstract public void p();
}

class LeafNode extends Node {
    String content;

    public LeafNode(String content){
        this.content = content;
    }

    @Override
    public void p() {
        System.out.println(content);
    }
}

class BranchNode extends Node {
    List<Node> nodes = new ArrayList<>();
    String name;

    public BranchNode(String name){
        this.name = name;
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    @Override
    public void p() {
        System.out.println(name);
    }
}
