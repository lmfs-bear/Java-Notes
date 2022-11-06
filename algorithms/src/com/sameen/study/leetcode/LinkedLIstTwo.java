package com.sameen.study.leetcode;

public class LinkedLIstTwo {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**
     * 二叉搜索树转换成双向链表
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Item res = recur(root);
        res.head.left = res.tail;
        res.tail.right = res.head;
        return res.head;
    }

    public Item recur(Node node) {
        if (node == null) {
            return null;
        }
        Item left = recur(node.left);
        Item right = recur(node.right);
        if (left != null) {
            node.left = left.tail;
            left.tail.right = node;
        }
        if (right != null) {
            node.right = right.head;
            right.head.left = node;
        }
        return new Item(left == null ? node : left.head, right == null ? node : right.tail);
    }

    class Item {
        Node head;
        Node tail;

        public Item(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

}

