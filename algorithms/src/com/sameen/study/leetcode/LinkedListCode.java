package com.sameen.study.leetcode;

import java.util.HashSet;
import java.util.Stack;

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
        if (head.next == null) {
            return head;
        }
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
            res = pre;
        }
        rightNode.next = next;
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

    /**
     * https://leetcode.cn/problems/lMSNwu/
     * f1:翻转链表 f2:利用栈逆序
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int c = 0;
        ListNode pre = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            Integer num1 = stack1.isEmpty() ? 0 : stack1.pop();
            Integer num2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = num1 + num2 + c;
            c = sum >= 10 ? 1 : 0;
            ListNode node = new ListNode(sum % 10);
            node.next = pre;
            pre = node;
        }
        if (c == 1) {
            ListNode node = new ListNode(1);
            node.next = pre;
            pre = node;
        }
        return pre;
    }

    /**
     * 两个单链表相交 有环、无环
     * 拆分子问题
     * 1、判断是否有环（一个有环相交） 有环和无环 不可能相交
     * 2、两个无环相交
     * 3、两个有环相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //无环相交 https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
//        if (headA == null || headB == null) {
//            return null;
//        }
//        ListNode pA = headA, pB = headB;
//        while (pA != pB) {
//            pA = pA == null ? headB : pA.next;
//            pB = pB == null ? headA : pB.next;
//        }
//        return pA;

        //两个有环相交
        return null;
    }

    /**
     * 链表中环的入口节点
     * https://leetcode.cn/problems/c32eOV/
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 将单链表的每K个节点之间逆序
     * https://www.nowcoder.com/practice/66285653d28b4ed6a15613477670e936?tpId=101&tqId=33187&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3Dk%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=k
     *
     * @param head
     * @param k
     */
    public ListNode reverseKNode(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        ListNode cur = head, pre = null, next = null, res = null, last = null;
        boolean flag = false;
        while (cur != null) {
            int n = k, m = 0;
            pre = null;
            ListNode tmp = cur;
            while (tmp != null && m < n) {
                tmp = tmp.next;
                m++;
            }
            if (n != m) {
                if (last != null) {
                    last.next = cur;
                }
                break;
            } else {
                ListNode lastCopy = last;
                last = cur;
                while (n-- > 0 && cur != null) {
                    next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                if (!flag) {
                    res = pre;
                } else {
                    lastCopy.next = pre;
                }
                flag = true;
            }
        }
        return res;
    }

    /**
     * 删除无序链表中值重复出现的节点
     * f1: hashtable O(N) O(N)
     * f2: O(N^2) O(1)
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //f2
//        ListNode cur = null, pre = null,res=head;
//        while (head != null) {
//            cur = head;
//            int val = cur.val;
//            pre = cur;
//            while (cur != null) {
//                cur = cur.next;
//                while (cur != null && cur.val == val) {
//                    cur = cur.next;
//                }
//                pre.next = cur;
//                pre = cur;
//            }
//            head = head.next;
//        }
//        return res;

        //f1

        //f2
        if (head == null) {
            return null;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode res = head, pre = head;
        while (head != null) {
            set.add(head.val);
            head = head.next;
            while (head != null && set.contains(head.val)) {
                head = head.next;
            }
            pre.next = head;
            pre = head;
        }
        return res;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode res = head, pre = null;
        while (head != null && head.val == val) {
            head = head.next;
        }
        res = head;
        while (head != null) {
            pre = head;
            head = head.next;
            if (head != null && head.val == val) {
                head = head.next;
                pre.next = head;
            }
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