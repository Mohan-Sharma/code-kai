package org.msharma.algorithm.priority_queue;

/**
 * @author Mohan Sharma
 */
public class MinHeap extends BinaryHeap{

    public MinHeap(int capacity) {
        super(capacity);
    }

    @Override
    public void heapifyUp() {
        int index = size - 1;
        while(hasParent(index) && getParent(index) > array[index]) {
            int parent = getParentIndex(index);
            swap(index, parent);
            index = parent;
        }
    }

    @Override
    public void heapifyDown() {
        int index = 0;
        while(hasLeftChildIndex(index)) {
            int smallIndex = getLeftChildIndex(index);
            if(hasRightChildIndex(index) && getRightChild(index) < array[smallIndex]) {
                smallIndex = getRightChildIndex(index);
            }
            if(array[index] < array[smallIndex])
                break;
            else {
                swap(index, smallIndex);
            }
            index = smallIndex;
        }
    }
}
