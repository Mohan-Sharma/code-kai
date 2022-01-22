package org.msharma.algorithm.priority_queue;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Mohan Sharma
 */
public class MaxHeap extends BinaryHeap {

    public MaxHeap(int capacity) {
        super(capacity);
    }

    @Override
    public void heapifyUp() {
        int index = size - 1;
        while(hasParent(index) && getParent(index) < array[index]) {
            int parent = getParentIndex(index);
            swap(index, parent);
            index = parent;
        }
    }

    @Override
    public void heapifyDown() {
        int index = 0;
        while(hasLeftChildIndex(index)) {
            int largeIndex = getLeftChildIndex(index);
            if(hasRightChildIndex(index) && getRightChild(index) > array[largeIndex]) {
                largeIndex = getRightChildIndex(index);
            }
            if(array[index] > array[largeIndex])
                break;
            else {
                swap(index, largeIndex);
            }
            index = largeIndex;
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        BinaryHeap binaryHeap = new MaxHeap(10);
        IntStream.range(1, 10).forEach(binaryHeap::push);
        System.out.println(Arrays.toString(binaryHeap.array));
        while(binaryHeap.size > 0)
            System.out.println(binaryHeap.poll());
    }
}
