package com.sameen.study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        candy(new int[]{1, 0, 2});
    }

    public static int candy(int[] ratings) {
        if (ratings.length == 1) {
            return 1;
        }
        int[] res = new int[ratings.length];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            res[i] = ratings[i] > ratings[i - 1] ? res[i - 1] + 1 : 1;
        }
        int count = 0;
        for (int i = res.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && res[i] <= res[i + 1]) {
                res[i] = res[i + 1] + 1;
            }
            count += res[i];
        }
        return count + res[res.length - 1];
    }

}

class TwoStackQueue {

    private Stack<Integer> stack;
    private Stack<Integer> popStack;

    /**
     * initialize your data structure here.
     */
    public TwoStackQueue() {
        stack = new Stack<>();
        popStack = new Stack<>();
    }

    public void add(int x) {
        stack.push(x);
    }

    public int poll() {
        if (popStack.isEmpty()) {
            while (!stack.empty()) {
                popStack.push(stack.pop());
            }
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            while (!stack.empty()) {
                popStack.push(stack.pop());
            }
        }
        return popStack.peek();
    }
}
