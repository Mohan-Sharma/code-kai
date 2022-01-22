package org.msharma.algorithm.stack;

/**
 * @author Mohan Sharma Created on 26/06/17.
 */
public class Stack {
    private transient int[] table;
    private int top = -1;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        table = new int[DEFAULT_CAPACITY];
    }

    public Stack(int capacity) {
        table = new int[capacity];
    }

    public void push(int data) {
        table[top++] = data;
    }

    public int pop() {
        int data = getTop();
        top--;
        return data;
    }

    private int getTop() {
        if(top  < 0)
            throw new IllegalStateException("Stack is empty.");
        return table[top];
    }
}
