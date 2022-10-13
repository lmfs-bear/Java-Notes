package com.sameen.study.leetcode;

/**
 * @author: zhangjinming on 2022/10/11
 * @description:
 */
public class LinkedListCode {

    /**
     * 删除链表的倒数第 n 个结点 单链表
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode next = head, cur = head;
        while (n-- > 0) {
            if (cur == null) {
                return head;
            }
            cur = cur.next;
        }
        if (cur == null) {
            return head.next;
        }
        while (cur.next != null) {
            cur = cur.next;
            next = next.next;
        }
        next.next = next.next.next;
        return head;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}