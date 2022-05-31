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

    // Idea: whenever the val is less the global min, store the global min once then store the val
    // and mark the value as min. In case of pop if the popped element is equals global min,
    // then pop again to set the prev min as new min
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
