package com.sameen.study.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author: zhangjinming on 2022/9/23
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(1);
        stack.push(7);
        stack.push(4);
        sortAnotherStack(stack);
        System.out.println(stack);
    }

    /**
     * sort another stack
     */
    public static void sortAnotherStack(Stack<Integer> stack) {
        Deque<Integer> helpStack = new ArrayDeque<>();
        while (!stack.empty()) {
            Integer pop = stack.pop();
            while (!helpStack.isEmpty() && helpStack.peek() > pop) {
                stack.push(helpStack.pop());
            }
            helpStack.push(pop);
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }
}
