package com.sameen.study.submit;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

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
//        PrintWriter out = new PrintWriter(System.out);
        getPairNum2();
    }

    /**
     * https://www.nowcoder.com/practice/16d1047e9fa54cea8b5170b156d89e38?tpId=101&rp=1&ru=%2Fexam%2Foj%2Fta&qru=%2Fexam%2Foj%2Fta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%259C%2580%25E5%25A4%25A7%25E5%2580%25BC%26tpId%3D101%26type%3D101&difficulty=&judgeStatus=&tags=&title=%E5%B1%B1%E5%B3%B0&gioEnter=menu
     * 可见的山峰对数量 含重复数字
     */
    public static void getPairNum2() throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int[] arr = new int[n];
        int maxIndex = 0, max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = reader.nextInt();
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }

        Stack<Record> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[(maxIndex + i) % n];
            while (!stack.isEmpty() && num > stack.peek().val) {
                Record pop = stack.pop();
                res += pop.times == 1 ? 2 : (pop.times * 2 + getCk2(pop.times));
            }
            if (!stack.isEmpty() && stack.peek().val == num) {
                Record pop = stack.pop();
                stack.push(new Record(pop.val, pop.times + 1));
            } else {
                stack.push(new Record(num, 1));
            }
        }

        while (!stack.isEmpty()) {
            //case 1
            if (stack.size() == 1) {
                int times = stack.pop().times;
                res += times == 1 ? 0 : getCk2(times);
                break;
            }
            //case 2
            if (stack.size() == 2) {
                int times = stack.pop().times;
                int PeekTimes = stack.peek().times;
                res += PeekTimes == 1 ? times + getCk2(times) : (times * 2 + getCk2(times));
            } else {
                //case 3
                int times = stack.pop().times;
                res += times * 2 + getCk2(times);
            }
        }
        System.out.println(res);
    }

    public static int getCk2(int k) {
        return k * (k - 1) / 2;
    }

    static class Record {
        int val;
        int times;

        public Record(int val, int times) {
            this.val = val;
            this.times = times;
        }
    }

}
