package top100.liked.easy.stack;

import java.util.Stack;

/**
 * @author Mohan Sharma
 */
public class MinStack {

    Stack<Integer> stack;
    int min = Integer.MAX_VALUE;
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (val <= min) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        return stack.peek();
    }

    public int getMin() {
        if (stack.isEmpty())
            throw new IllegalStateException("Stack is empty.");
        return min;
    }
}
