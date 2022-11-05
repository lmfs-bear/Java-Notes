package com.sameen.study.test;

import javax.sound.sampled.ReverbType;

/**
 * @author: zhangjinming on 2022/9/23
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
//        node.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        node2.next = node4;
        reverseBetween(node2, 1, 2);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null) return head;
        int n = right - left + 1;
        ListNode pre = null, next = null, res = head, leftNode = head, rightNode = head;
        if (left > 1) {
            while (left-- > 2) {
                head = head.next;
                leftNode = leftNode.next;
            }
            head = head.next;
            rightNode = head;
        }
        while (n-- > 0) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        if (leftNode != rightNode) {
            leftNode.next = pre;
        } else {
            res=pre;
        }
        rightNode.next = next;
        return res;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
