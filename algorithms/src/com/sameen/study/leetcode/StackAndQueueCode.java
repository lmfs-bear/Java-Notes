package com.sameen.study.leetcode;

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

    public static void main(String[] args) {
        List<int[]> res = singleStack(new int[]{3, 4, 1, 5, 6, 2, 7});
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
     *
     * @param arr
     * @return
     */
    public static List<int[]> singleStack(int[] arr) {
        //example: 3 4 1 5 6 2 7
        Deque<Integer> leftStack = new ArrayDeque<>();
        Deque<Integer> rightStack = new ArrayDeque<>();
        List<int[]> res = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            res.add(new int[]{-1, -1});
        }
        for (int i = 0; i < arr.length; i++) {
            while (!leftStack.isEmpty() && arr[i] < arr[leftStack.peekLast()]) {
                int[] tmp = res.get(leftStack.pollLast());
                tmp[1] = i;
            }
            while (!rightStack.isEmpty() && arr[arr.length - i - 1] < arr[rightStack.peekLast()]) {
                int[] tmp = res.get(rightStack.pollLast());
                tmp[0] = arr.length - i - 1;
            }
            leftStack.addLast(i);
            rightStack.addLast(arr.length - i - 1);
        }
        return res;
    }

}
