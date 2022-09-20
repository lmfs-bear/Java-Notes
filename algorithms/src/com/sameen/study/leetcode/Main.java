package com.sameen.study.leetcode;

import java.util.LinkedList;

/**
 * @author: zhangjinming on 2022/9/20
 * @description:
 */
public class Main {

}

class MinStack {

    private LinkedList<Integer> stack;
    private LinkedList<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(minStack.size() == 0 ? x : Math.min(minStack.peek(), x));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}