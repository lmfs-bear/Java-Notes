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
     * 二叉搜索树转换成双向链表 https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
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

    /**
     * 链表的选择排序 空间 O(1)
     *
     * @param head
     * @return
     */
    public ListNode selectionSort(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = null, tmp = null, pre = new ListNode(0);
        ListNode res = pre, last = null, next = null;
        while (head != null) {
            cur = head;
            int min = head.val;
            last = null;
            next = null;
            while (cur != null) {
                if (cur.val <= min) {
                    tmp = cur;
                    next = last;
                    min = cur.val;
                }
                last = cur;
                cur = cur.next;
            }
            if (tmp == head) {
                head = head.next;
            }
            if (next != null) {
                next.next = tmp.next;
            }
            tmp.next = null;
            pre.next = tmp;
            pre = pre.next;
        }
        return res.next;
    }

    /**
     * 向有序的环形单链表中插入新节点
     * https://www.nowcoder.com/practice/8a2ed8d048f241fd92b478140bad18a1?tpId=101&tqId=33226&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%258F%2592%25E5%2585%25A5%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%8F%92%E5%85%A5
     *
     * @param head
     * @param num
     * @return
     */
    public ListNode insert(ListNode head, int num) {
        ListNode node = new ListNode(num);
        if (head == null || head.val >= num) {
            node.next = head;
            return node;
        }
        ListNode pre = null, res = head;
        while (head != null && num >= head.val) {
            pre = head;
            head = head.next;
        }
        node.next = pre.next;
        pre.next = node;
        return res;
    }

    /**
     * 合并两个有序链表
     * O(N+M) O(1)
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode node = new ListNode(0);
        ListNode pre = node;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) {
            node.next = list1;
        }
        if (list2 != null) {
            node.next = list2;
        }
        return pre.next;
    }

    /**
     * 按照左右半区的方式重新组合单链表
     * https://www.nowcoder.com/practice/a7a348bdb4634e228cf7704c8a2a8bda?tpId=101&tqId=33228&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E5%25B7%25A6%25E5%258F%25B3%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E5%B7%A6%E5%8F%B3
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode combineLists(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head, cur = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l1 = head, l2 = slow;
        while (true) {
            l1 = l1.next;
            cur.next = l2;
            cur = cur.next;
            l2 = l2.next;
            if (l1 == slow) {
                cur.next = l2;
                break;
            }
            cur.next = l1;
            cur = cur.next;
        }
        return head;
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

