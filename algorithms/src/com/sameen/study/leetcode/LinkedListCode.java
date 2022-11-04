package com.sameen.study.leetcode;

import java.util.List;

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
        if (head == null || head.next == null) {
            return true;
        }
        ListNode p1 = head, p2 = head, first = null;
        while (p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }
        first = p1.next;
        p1.next = null;
        first = reverse(first);
        while (first != null) {
            if (first.val != head.val) {
                return false;
            }
            head = head.next;
            first = first.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 将单向链表按某值划分为左边小，中间相等，右边大的形式
     * https://www.nowcoder.com/practice/04fcabc5d76e428c8100dbd855761778?tpId=101&tqId=33181&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%258C%2589%25E6%259F%2590%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%8C%89%E6%9F%90
     */
    public ListNode listPartition(ListNode head, int pivot) {
        return null;
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