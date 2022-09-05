package com.code.kai.leetcode.dojo.easy.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohan Sharma
 */
public class MinStackUsingObject {

    class MinTracker {
        private int min;
        private int val;

        public MinTracker(int min, int val) {
            this.min = min;
            this.val = val;
        }
    }

    List<MinTracker> list;
    public MinStackUsingObject() {
        list = new ArrayList<>();
    }

    public void push(int val) {
        if (list.isEmpty()) {
            list.add(new MinTracker(val, val));
        } else {
            MinTracker top = list.get(list.size() - 1);
            list.add(new MinTracker(Math.min(top.min, val), val));
        }
    }

    public void pop() {
        if (list.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        list.remove(list.size() - 1);
    }

    public int top() {
        if (list.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        MinTracker top = list.get(list.size() - 1);
        return top.val;
    }

    public int getMin() {
        if (list.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        MinTracker top = list.get(list.size() - 1);
        return top.min;
    }
}
