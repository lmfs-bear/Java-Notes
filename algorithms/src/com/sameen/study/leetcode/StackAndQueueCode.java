package com.sameen.study.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: zhangjinming on 2022/9/23
 * @description:
 */
public class StackAndQueueCode {

    public static void main(String[] args) {

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
     * @param arr
     * @return
     */
    public static List<int[]> singleStack(int[] arr) {

    }

}
