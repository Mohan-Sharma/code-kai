package org.msharma.algorithm.priority_queue;

import java.util.Arrays;

/**
 * @author Mohan Sharma
 */
public abstract class BinaryHeap {

    protected int[] array;
    protected int size;
    private int capacity;

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public BinaryHeap() {
        this(10);
    }

    public int size() {
        return size;
    }

    public int getParentIndex(int index) {
       return  (index - 1) / 2;
    }

    public int getParent(int index) {
        return  array[getParentIndex(index)];
    }

    public int getLeftChildIndex(int index) {
        return (2 * index + 1);
    }

    public int getRightChildIndex(int index) {
        return (2 * index + 2);
    }

    public boolean hasParent(int childIndex) {
        return getParentIndex(childIndex) >= 0;
    }

    public boolean hasLeftChildIndex(int parent) {
        return getLeftChildIndex(parent) < size;
    }

    public boolean hasRightChildIndex(int parent) {
        return getRightChildIndex(parent) < size;
    }


    public int getLeftChild(int parent) {
        return array[getLeftChildIndex(parent)];
    }

    public int getRightChild(int parent) {
        return array[getRightChildIndex(parent)];
    }

    public void ensureInternalCapacity() {
        if(size >= capacity) {
            capacity += capacity >> 1;
            array = Arrays.copyOf(array, capacity);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() throws IllegalAccessException {
        if(size == 0)
            throw new IllegalAccessException("Heap is empty!!");
        return array[0];
    }

    public void push(int data) {
        ensureInternalCapacity();
        array[size++] = data;
        heapifyUp();
    }

    public int poll() throws IllegalAccessException {
        int data = peek();
        array[0] = array[size - 1];
        size--;
        heapifyDown();
        return data;
    }

    protected void swap(int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    public abstract void heapifyUp();

    public abstract void heapifyDown();
}
