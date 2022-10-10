package com.sameen.study.leetcode;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: zhangjinming on 2022/9/23
 * @description:
 */
public class StackAndQueueCode {

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

    public static void main(String[] args) {
        List<int[]> res = getNearLessNoRepeat(new int[]{3, 4, 1, 5, 6, 2, 7});
        List<int[]> list = getNearLessRepeat(new int[]{3, 1, 3, 4, 3, 5, 3, 2, 2});
        System.out.println(list);
    }

    /**
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= k && nums[i - k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(nums[i]);
            if (i >= k - 1) {
                res[index++] = deque.peekFirst();
            }
        }
        return res;
    }

    /**
     * 给定一个不含有重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置。返回所有位置相应的信息。
     * example: 3 4 1 5 6 2 7
     *
     * @param arr
     * @return
     */
    public static List<int[]> getNearLessNoRepeat(int[] arr) {
        Deque<Integer> leftStack = new ArrayDeque<>();
        List<int[]> res = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            res.add(new int[]{-1, -1});
        }
        for (int i = 0; i < arr.length; i++) {
            while (!leftStack.isEmpty() && arr[i] < arr[leftStack.peekLast()]) {
                int[] tmp = res.get(leftStack.pollLast());
                tmp[1] = i;
                tmp[0] = leftStack.isEmpty() ? -1 : leftStack.peekLast();
            }
            leftStack.addLast(i);
        }
        while (!leftStack.isEmpty()) {
            int[] tmp = res.get(leftStack.pollLast());
            tmp[0] = leftStack.isEmpty() ? -1 : leftStack.peekLast();
        }
        return res;
    }

    /**
     * 含有重复值的数组arr
     * example:{3，1，3，4，3，5，3，2，2}
     *
     * @param arr
     * @return
     */
    public static List<int[]> getNearLessRepeat(int[] arr) {
        Deque<List<Integer>> stack = new ArrayDeque<>();
        List<int[]> res = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            res.add(new int[]{-1, -1});
        }
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peekLast().get(stack.peekLast().size() - 1)]) {
                List<Integer> pollLast = stack.pollLast();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peekLast().get(stack.peekLast().size() - 1);
                for (Integer integer : pollLast) {
                    int[] tmp = res.get(integer);
                    tmp[1] = i;
                    tmp[0] = leftLessIndex;
                }
            }
            if (!stack.isEmpty() && arr[i] == arr[stack.peekLast().get(0)]) {
                stack.peekLast().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.addLast(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pollLast = stack.pollLast();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peekLast().get(stack.peekLast().size() - 1);
            for (Integer integer : pollLast) {
                int[] tmp = res.get(integer);
                tmp[0] = leftLessIndex;
            }
        }
        return res;
    }

    /**
     * https://www.nowcoder.com/practice/5fe02eb175974e18b9a546812a17428e?tpId=101&tqId=33086&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%259C%2580%25E5%25A4%25A7%25E5%2580%25BC%26tpId%3D101%26type%3D101&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%9C%80%E5%A4%A7%E5%80%BC
     * 最大值减去最小值小于或等于num的子数组数量
     */
    public static void getNum() throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int num = reader.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = reader.nextInt();
        }
    }

}