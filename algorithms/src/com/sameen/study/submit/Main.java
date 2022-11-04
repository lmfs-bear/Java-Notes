package com.sameen.study.submit;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: zhangjinming on 2022/10/11
 * @description:
 */
public class Main {

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int pivot = reader.nextInt();
        ListNode head = new ListNode(reader.nextInt());
        ListNode pre = head;
        for (int i = 1; i < n; i++) {
            head.next = new ListNode(reader.nextInt());
            head = head.next;
        }
        ListNode res = listPartition(pre, pivot);
        StringBuilder builder = new StringBuilder();
        while (res != null) {
            builder.append(res.val + " ");
            res = res.next;
        }
        System.out.println(builder.toString());
    }

    /**
     * 将单向链表按某值划分为左边小，中间相等，右边大的形式
     * 时间复杂度 O(N) 空间复杂度 O(1)
     * https://www.nowcoder.com/practice/04fcabc5d76e428c8100dbd855761778?tpId=101&tqId=33181&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%258C%2589%25E6%259F%2590%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%8C%89%E6%9F%90
     */
    public static ListNode listPartition(ListNode head, int pivot) {
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

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
