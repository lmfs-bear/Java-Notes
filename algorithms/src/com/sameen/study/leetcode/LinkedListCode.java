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
     * 时间复杂度 O(N) 空间复杂度 O(1)
     * https://www.nowcoder.com/practice/04fcabc5d76e428c8100dbd855761778?tpId=101&tqId=33181&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%258C%2589%25E6%259F%2590%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%8C%89%E6%9F%90
     */
    public ListNode listPartition(ListNode head, int pivot) {
        ListNode ss = null, se = null, ms = null, me = null, bs = null, be = null;
        while (head != null) {
            ListNode tmp = head;
            head = head.next;
            tmp.next = null;
            if (tmp.val < pivot) {
                if (ss == null) {
                    ss = tmp;
                    se = tmp;
                } else {
                    se.next = tmp;
                    se = se.next;
                }
            } else if (tmp.val > pivot) {
                if (bs == null) {
                    bs = tmp;
                    be = tmp;
                } else {
                    be.next = tmp;
                    be = be.next;
                }
            } else {
                if (ms == null) {
                    ms = tmp;
                    me = tmp;
                } else {
                    me.next = tmp;
                    me = me.next;
                }
            }
        }
        ListNode res = ss == null ? (ms == null ? bs : ms) : ss;
        if (ss != null) {
            se.next = ms == null ? bs : ms;
        }
        if (ms != null) {
            me.next = bs == null ? null : bs;
        }
        return res;
    }

    /**
     * 复制带随机指针的链表
     * https://leetcode.cn/problems/copy-list-with-random-pointer/
     * 时间复杂度 O(N) 空间复杂度 O(1)
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head, copy = null, res = null;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        res = head.next;
        while (cur != null) {
            copy = cur.next;
            copy.random = cur.random == null ? null : cur.random.next;
            cur = copy.next;
        }
        cur = head;
        while (cur != null) {
            copy = cur.next;
            cur.next = copy.next;
            cur = copy.next;
            copy.next = cur == null ? null : cur.next;
        }
        return res;
    }
}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
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