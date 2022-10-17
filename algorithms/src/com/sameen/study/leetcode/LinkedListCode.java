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

    /**
     * 删除链表的中间节点
     *
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode cur = head, next = head.next.next;
        while (next != null && next.next != null) {
            next = next.next;
            next = next.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

    /**
     * reverse list-node II
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head.next == null) return head;
        int n = right - left + 1;
        ListNode pre = head, next = null, res = head, leftNode = head;
        head = head.next;
        while (left-- > 2) {
            head = head.next;
            leftNode = leftNode.next;
        }
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
        while (n-- > 0) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        leftNode.next.next = head;
        leftNode.next = pre;
        return res;
    }


    /**
     * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     * 约瑟夫环
     */
    public int lastRemaining(int n, int m) {
        return n == 1 ? 0 : (m + lastRemaining(n - 1, m)) % n;
    }

    /**
     * 回文链表
     * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        return false;
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